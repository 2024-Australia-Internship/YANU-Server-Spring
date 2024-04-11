package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.favorite.RegisterHeartDto;
import com.bbogle.yanu.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<String> registerHeart(@RequestBody RegisterHeartDto request){
        favoriteService.registerHeart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("하트 등록에 성공했습니다.");
    }
}
