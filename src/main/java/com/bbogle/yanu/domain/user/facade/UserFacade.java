package com.bbogle.yanu.domain.user.facade;

import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.EmailDuplicateException;
import com.bbogle.yanu.global.exception.PasswordNotFoundException;
import com.bbogle.yanu.global.exception.PhoneNumberDuplicateException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class UserFacade {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void duplicateEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new EmailDuplicateException("email duplicated", ErrorCode.EMAIL_DUPLICATION);
        }
    }

    public void duplicatePhonenumber(String phonenumber){
        if(userRepository.existsByPhonenumber(phonenumber)){
            throw new PhoneNumberDuplicateException("phone duplicated", ErrorCode.PHONENUMBER_DUPLICATION);
        }
    }

    public void checkEmail(String email){
        if(!userRepository.existsByEmail(email)){
            throw new EmailDuplicateException("email not found", ErrorCode.EMAIL_NOTFOUND);
        }
    }
    public void checkPassword(String password, String hashedPassword){
        if(!passwordEncoder.matches(password, hashedPassword)){
            throw new PasswordNotFoundException("password not found", ErrorCode.PASSWORD_NOTFOUND);
        }
    }
}
