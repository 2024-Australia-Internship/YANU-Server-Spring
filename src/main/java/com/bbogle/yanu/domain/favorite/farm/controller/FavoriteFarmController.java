package com.bbogle.yanu.domain.favorite.farm.controller;

import com.bbogle.yanu.domain.favorite.farm.dto.RegisterFarmHeartRequestDto;
import com.bbogle.yanu.domain.favorite.farm.service.RegisterFarmHeartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites/farms")
public class FavoriteFarmController {
    private final RegisterFarmHeartService registerHeartService;

    @PostMapping
    public ResponseEntity<String> registerHeart(@RequestBody RegisterFarmHeartRequestDto request, HttpServletRequest httpRequest){
        registerHeartService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("하트 등록에 성공했습니다.");
    }

}
