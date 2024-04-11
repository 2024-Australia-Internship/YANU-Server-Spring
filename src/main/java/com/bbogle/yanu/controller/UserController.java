package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.user.*;
import com.bbogle.yanu.entity.UserEntity;
import com.bbogle.yanu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
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

    @GetMapping("/{user_id}")
    public ResponseEntity<UserIdResponseDto> findByUser (@PathVariable("user_id") Long id){
        UserEntity user = userService.findByUser(id);
        return ResponseEntity.ok().body(new UserIdResponseDto(user));
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
}
