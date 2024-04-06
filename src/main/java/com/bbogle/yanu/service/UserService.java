package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.user.RegisterRequestDto;
import com.bbogle.yanu.exception.EmailDuplicateException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequestDto request){
        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());

        duplicateEmail(email);

        userRepository.save(request.toEntity(password));
    }

    void duplicateEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new EmailDuplicateException("email duplicated", ErrorCode.EMAIL_DUPLICATION);
        }
    }

}
