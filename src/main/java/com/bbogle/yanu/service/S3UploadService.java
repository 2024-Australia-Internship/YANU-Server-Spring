package com.bbogle.yanu.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bbogle.yanu.entity.UserEntity;
import com.bbogle.yanu.exception.EmailNotFoundException;
import com.bbogle.yanu.exception.SessionNotFoundException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final UserRepository userRepository;

    @Value("${could.aws.s3.bucket}")
    private String bucketName;

    public String uploadProfile(String email, MultipartFile file) throws IOException {
        if(!userRepository.existsByEmail(email))
            throw new EmailNotFoundException("email not found", ErrorCode.EMAIL_NOTFOUND);

        String fileName = generateFileName(email, file);
        String fileURL = uploadFileToS3(fileName, file);
        return fileURL;
    }

    private String generateFileName(String email, MultipartFile file){
        return UUID.randomUUID().toString() + "_" + "profile" + "_" + email + "_" + file.getOriginalFilename();
    }

    private String uploadFileToS3(String fileName, MultipartFile file) throws IOException{
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

}
