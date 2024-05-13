package org.learn.springbootlearning.minioservice;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MinioHandler {
    private static final Logger logger = getLogger(MinioHandler.class);

    private static final MinioClient minioClient = MinioUtility.minioClient;

    public static MinioHandler minioHandler = null;

    public static MinioHandler getMinioHandler() {
        if (minioHandler == null) {
            minioHandler = new MinioHandler();
        }
        return minioHandler;
    }

    public void addFileToMinio() throws Exception {
        // Make 'test1829' bucket if not exist.
        boolean bucketExists =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket("test1829").build());

        if (!bucketExists) {
            // Make a new bucket called 'test1829'.
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("test1829").build());
            logger.info("Bucket 'test1829' doesn't exists, creating the bucket");
        } else {
            logger.info("Bucket 'test1829' already exists.");
        }

        // Upload '/download/sample-zip-file.zip' as object name 'sample.zip' to bucket 'test1829'.
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket("test1829")
                        .object("sample.zip")
                        .filename("/download/sample-zip-file.zip")
                        .build());
    }
}

/*
MinioHandler minioHandler = MinioHandler.getMinioHandler();
		minioHandler.addFileToMinio();
		logger.info("added file to minio");
 */
