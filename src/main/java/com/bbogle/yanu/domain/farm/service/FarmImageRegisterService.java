package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import com.bbogle.yanu.global.exception.FarmNotFoundException;
import com.bbogle.yanu.global.exception.UserNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FarmImageRegisterService {
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final S3UploadService s3UploadService;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(MultipartFile profile, HttpServletRequest httpRequest) throws IOException {
        String token = tokenValidator.validateToken(httpRequest);
        Long userId = tokenProvider.getUserId(token);

        UserEntity user = userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("user not found", ErrorCode.USER_NOTFOUND));

        String email = user.getEmail();
        String profileUrl = s3UploadService.uploadProfile(email, profile);
        FarmEntity farm = farmRepository.findByUserId(userId);

        farm.updateProfile(profileUrl);

        farmRepository.save(farm);
    }
}
