package org.learn.springbootlearning.watcherservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
Implemented a watcher -> it watches a directory and if anything happens within the directory then it gets trigger and
perform the specified task.
 */
@Service
public class WatcherService {

    private static final Logger logger = LoggerFactory.getLogger(WatcherService.class);
    private final String directoryToWatch = "C:\\Users\\vg539\\Videos\\failedFiles";
    private static final int RETRY_DELAY_MINUTES = 1;
    private static final int MAX_RETRIES = 3;

    @PostConstruct
    public void init() {
        try {
            Path path = Paths.get(directoryToWatch);
            if (!Files.exists(path)) {
                logger.info("directoryToWatch - {} doesn't exist, creating the directory.", directoryToWatch);
                Files.createDirectories(path);
            }

            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            Thread watchThread = new Thread(() -> {
                try {
                    watchEvents(watchService);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            watchThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void watchEvents(WatchService watchService) throws IOException, InterruptedException {
        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    logger.info("File created in " + directoryToWatch);

                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path createdFile = Paths.get(directoryToWatch).resolve(ev.context());

                    boolean success = false;

                    // Convert the created file to a java.io.File object
                    File file = createdFile.toFile();
                    logger.info("File name: " + file.getName());
//                    int ind = file.getName().lastIndexOf("_");

                    int retries = 0;
                    while (!success && retries < MAX_RETRIES) {

                        try {
                            process(retries);
                            logger.info("All xns are loaded from file {}", file.getName());
                            success = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        retries++;
                        if (!success) {
                            logger.error("loading xns to db failed. Retrying after {} minutes.", RETRY_DELAY_MINUTES);
                            TimeUnit.MINUTES.sleep(RETRY_DELAY_MINUTES);
                        }
                    }

                    if (success) {
                        deleteFile(createdFile);
                    }
                }
            }
            key.reset();
        }
    }

    private void process(int retries) throws Exception {
        if (retries < 2) {
            throw new Exception("testing...........");
        }

        logger.info("Success...............");
    }

    private void deleteFile(Path filePath) {
        try {
            Files.delete(filePath);
            logger.info("Deleted file: {}", filePath.getFileName());
        } catch (IOException e) {
            logger.error("Error deleting file: {}", filePath.getFileName());
            e.printStackTrace();
        }
    }
}

