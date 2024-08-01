package com.InternalManagementSystem.IMS.service.serviceImplementation;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class UploadService {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.bucketName}")
    String BUCKET_NAME;

    public String uploadFile(MultipartFile imageFile) throws IOException {
        // Generate a unique filename for the image
        String imageFilename = UUID.randomUUID().toString() + "." + imageFile.getOriginalFilename().split("\\.")[1];



        // Prepare metadata for the image
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageFile.getSize());

        // Upload the image to the specified S3 bucket
        amazonS3.putObject(BUCKET_NAME, imageFilename, imageFile.getInputStream(), metadata);

        // Create a request to generate a pre-signed URL for accessing the uploaded image
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME, imageFilename)
                .withMethod(com.amazonaws.HttpMethod.GET);

        // Generate the pre-signed URL
        URL signedUrl = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return signedUrl.toString();
    }
}