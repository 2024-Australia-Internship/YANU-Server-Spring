package com.bbogle.yanu.global.S3Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.global.exception.EmailNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.domain.user.repository.UserRepository;
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

    public void uploadProfile(String email, MultipartFile file) throws IOException {
        if(!userRepository.existsByEmail(email))
            throw new EmailNotFoundException("email not found", ErrorCode.EMAIL_NOTFOUND);

        String fileName = generateFileName(email);

        UserEntity user = userRepository.findByEmail(email);
        user.updateProfileImg(fileName);
        userRepository.save(user);

        uploadFileToS3(fileName, file);
    }

    private String generateFileName(String email){
        return UUID.randomUUID().toString() + "_" + "profile" + "_" + email;
    }

    private String uploadFileToS3(String fileName, MultipartFile file) throws IOException{
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    public String getFilePath(String email){
        UserEntity user = userRepository.findByEmail(email);
        String imgPath = user.getProflie_image();
        return amazonS3.getUrl(bucketName , imgPath).toString();
    }

}
