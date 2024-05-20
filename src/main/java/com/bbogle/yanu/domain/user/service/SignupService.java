package com.bbogle.yanu.domain.user.service;

import com.bbogle.yanu.domain.user.dto.RegisterRequestDto;
import com.bbogle.yanu.domain.user.facade.UserFacade;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.EmailDuplicateException;
import com.bbogle.yanu.global.exception.PhoneNumberDuplicateException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Transactional
    public void execute(RegisterRequestDto request){
        String email = request.getEmail();
        String phonenumber = request.getPhonenumber();
        String password = passwordEncoder.encode(request.getPassword());

        userFacade.duplicateEmail(email);
        userFacade.duplicatePhonenumber(phonenumber);

        userRepository.save(request.toEntity(password));
    }

}
