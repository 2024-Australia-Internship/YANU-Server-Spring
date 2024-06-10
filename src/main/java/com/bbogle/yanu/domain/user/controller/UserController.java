package com.bbogle.yanu.domain.user.controller;

import com.bbogle.yanu.domain.user.dto.*;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.service.*;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final FindMyInfoService findMyInfoService;
    private final FindOtherInfoService findOtherInfoService;
    private final EmailDuplicateService emailDuplicateService;
    private final PasswordUpdateService passwordUpdateService;
    private final S3UploadService s3UploadService;
    private final ProfileInfoUploadService profileInfoUploadService;

    @PostMapping
    public ResponseEntity<String> signupUser(@RequestBody RegisterRequestDto request) {
        signupService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공했습니다");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser (@RequestBody LoginRequestDto request){
        String token = loginService.execute(request);
        return ResponseEntity.ok().body(new LoginResponseDto(token));
    }

    @GetMapping
    public ResponseEntity<UserIdResponseDto> findmyInfo (HttpServletRequest httpRequest){
        UserEntity user = findMyInfoService.execute(httpRequest);
        return ResponseEntity.ok().body(new UserIdResponseDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserIdResponseDto> findOtherInfo (@PathVariable("id") Long id,
                                                            HttpServletRequest request){
        UserEntity user = findOtherInfoService.execute(id, request);
        return ResponseEntity.ok().body(new UserIdResponseDto(user));
    }

    @PostMapping("/duplication/email")
    public ResponseEntity<String> duplicateEmail (@RequestBody EmailDuplicateRequestDto request){
        emailDuplicateService.execute(request);
        return ResponseEntity.ok().body("사용 가능한 이메일 입니다.");
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword (@RequestBody PasswordUpdateRequestDto request) {
        passwordUpdateService.execute(request);
        return ResponseEntity.ok().body("비밀번호 변경에 성공하였습니다.");
    }

    @PostMapping(value = "/profile/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadProfileImg(@RequestParam(value = "profile") MultipartFile file,
                                                   @RequestParam(value = "email") String email) {
        try {
            s3UploadService.uploadProfile(email, file);
            return ResponseEntity.ok().body("프로필 이미지 업로드에 성공했습니다");
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("프로필 이미지 업로드에 실패했습니다");
        }
    }

    @PostMapping("/profile/info")
    public ResponseEntity<String> uploadProfileInfo( @RequestBody RegisterProfileRequestDto request){
        profileInfoUploadService.execute(request);
        return ResponseEntity.ok().body("닉네임 등록에 성공했습니다.");
    }
}
