package com.bbogle.yanu.domain.user.service;

import com.bbogle.yanu.domain.user.dto.*;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.global.exception.*;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3UploadService s3UploadService;

    public void registerUser(RegisterRequestDto request){
        String email = request.getEmail();
        String phonenumber = request.getPhonenumber();
        String password = passwordEncoder.encode(request.getPassword());

        duplicateEmail(email);
        duplicatePhonenumber(phonenumber);

        userRepository.save(request.toEntity(password));
    }
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

    public Long loginUser(LoginRequestDto request, HttpServletRequest httpRequest){
        String email = request.getEmail();
        String password = request.getPassword();

        checkEmail(email);

        UserEntity userEntity = userRepository.findByEmail(email);
        checkPassword(password, userEntity.getPassword());

        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("userId", userEntity.getId());
        session.setMaxInactiveInterval(1800);

        return userEntity.getId();
    }
    private void checkEmail(String email){
        if(!userRepository.existsByEmail(email)){
            throw new EmailDuplicateException("email not found", ErrorCode.EMAIL_NOTFOUND);
        }
    }
    private void checkPassword(String password, String hashedPassword){
        if(!passwordEncoder.matches(password, hashedPassword)){
            throw new PasswordNotFoundException("password not found", ErrorCode.PASSWORD_NOTFOUND);
        }
    }

    public UserEntity findById (HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession(false);
        if(session == null){
            throw new SessionNotFoundException("sessoin not found", ErrorCode.SESSION_NOTFOUND);
        }
        Long userId = (Long) session.getAttribute("userId");

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found", ErrorCode.USER_NOTFOUND));
        return user;
    }

    public UserEntity findByUser (FindByUserRequestDto request){
        Long id = request.getId();
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found", ErrorCode.USER_NOTFOUND));
    }


    public void updatePassword(PasswordUpdateRequestDto request){
        String email = request.getEmail();
        String password = request.getPassword();
        checkEmail(email);

        UserEntity userEntity = userRepository.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(password);

        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
    }

    public void uploadProfileInfo(String email, RegisterProfileRequestDto request){

        if(!userRepository.existsByEmail(email))
            throw new EmailNotFoundException("email not found", ErrorCode.EMAIL_NOTFOUND);

        String nickname = request.getNickname();
        UserEntity userEntity = userRepository.findByEmail(email);
        userEntity.setNickname(nickname);
        userRepository.save(userEntity);
    }

}
