package com.bbogle.yanu.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3UploadService {
    private final AmazonS3 amazonS3;

    @Value("${could.aws.s3.bucket}")
    private String bucketName;

    public String uploadProfile(MultipartFile file) throws IOException {
        String fileName = generateFileName(file);
        String fileURL = uploadFileToS3(fileName, file);
        return fileURL;
    }

    private String generateFileName(MultipartFile file){
        return UUID.randomUUID().toString() + "_" + "profile" + "_" + file.getOriginalFilename();
    }

    private String uploadFileToS3(String fileName, MultipartFile file) throws IOException{
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
        return amazonS3.getUrl(bucketName, fileName).toString();
    }
}
