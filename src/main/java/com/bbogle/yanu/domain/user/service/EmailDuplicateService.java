package com.bbogle.yanu.domain.user.service;

import com.bbogle.yanu.domain.user.dto.EmailDuplicateRequestDto;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.EmailDuplicateException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmailDuplicateService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public void execute(EmailDuplicateRequestDto request){
        String email = request.getEmail();

        if(userRepository.existsByEmail(email)){
            throw new EmailDuplicateException("email duplicated", ErrorCode.EMAIL_DUPLICATION);
        }
    }
}
