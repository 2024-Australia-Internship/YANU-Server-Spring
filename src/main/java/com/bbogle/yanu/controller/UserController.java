package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.user.LoginRequestDto;
import com.bbogle.yanu.dto.user.RegisterRequestDto;
import com.bbogle.yanu.dto.user.LoginResponseDto;
import com.bbogle.yanu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto request) {
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공했습니다");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser (@RequestBody LoginRequestDto request){
        Long id = userService.loginUser(request);
        return ResponseEntity.ok().body(new LoginResponseDto(id));
    }


}
