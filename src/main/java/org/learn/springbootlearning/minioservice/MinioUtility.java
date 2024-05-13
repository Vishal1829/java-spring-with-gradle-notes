package org.learn.springbootlearning.minioservice;

import io.minio.MinioClient;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MinioUtility {

    private static final Logger logger = getLogger(MinioHandler.class);

    public static MinioClient minioClient;

    static {
        minioClient = getMinioClient();
    }

    private static MinioClient getMinioClient() {
        // Create a minioClient with the MinIO server playground, its access key and secret key.
        MinioClient client = null;
        client = MinioClient.builder()
//				.endpoint("https://play.min.io")
                .endpoint("https://play.min.io", 443, true)
                .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                .build();
        return client;
    }
}

/*
https://min.io/docs/minio/linux/developers/java/minio-java.html
 */
