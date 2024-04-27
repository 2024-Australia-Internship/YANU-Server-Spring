package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.user.*;
import com.bbogle.yanu.entity.UserEntity;
import com.bbogle.yanu.service.S3UploadService;
import com.bbogle.yanu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final S3UploadService s3UploadService;
    private final HttpServletRequest httpServletRequest;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto request) {
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공했습니다");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser (@RequestBody LoginRequestDto request){
        Long id = userService.loginUser(request, httpServletRequest);
        return ResponseEntity.ok()
                .body(new LoginResponseDto(id));
    }

    @GetMapping
    public ResponseEntity<UserIdResponseDto> findById (HttpServletRequest httpRequest){
        UserEntity user = userService.findById(httpRequest);
        String profile_img = s3UploadService.getFilePath(user.getEmail());
        return ResponseEntity.ok().body(new UserIdResponseDto(user, profile_img));
    }


    @GetMapping("/{user_id}")
    public ResponseEntity<UserIdResponseDto> findByUser(@PathVariable("user_id") Long id){
        UserEntity user = userService.findByUser(id);
        return ResponseEntity.ok().body(new UserIdResponseDto(user, ""));
    }

    @PostMapping("/duplication/{email}")
    public ResponseEntity<String> duplicateEmail (@PathVariable("email") String email){
        userService.duplicateEmail(email);
        return ResponseEntity.ok().body("사용 가능한 이메일 입니다.");
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword (@RequestBody PasswordUpdateRequestDto request) {
        userService.updatePassword(request);
        return ResponseEntity.ok().body("비밀번호 변경에 성공하였습니다.");
    }

    @PostMapping(value = "/profile/img/{email}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadProfileImg(@RequestParam(value = "profile") MultipartFile file, @PathVariable("email") String email) {
        try {
            s3UploadService.uploadProfile(email, file);
            return ResponseEntity.ok().body("프로필 이미지 업로드에 성공했습니다");
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("프로필 이미지 업로드에 실패했습니다");
        }
    }

    @PostMapping("/profile/info/{email}")
    public ResponseEntity<String> uploadProfileInfo(@PathVariable("email") String email, @RequestBody RegisterProfileRequestDto request){
        userService.uploadProfileInfo(email, request);
        return ResponseEntity.ok().body("닉네임 등록에 성공했습니다.");
    }
}
