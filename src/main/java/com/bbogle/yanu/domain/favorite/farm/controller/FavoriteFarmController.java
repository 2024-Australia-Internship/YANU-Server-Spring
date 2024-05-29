package com.bbogle.yanu.domain.favorite.farm.controller;

import com.bbogle.yanu.domain.favorite.farm.dto.DeleteFarmHeartRequestDto;
import com.bbogle.yanu.domain.favorite.farm.dto.RegisterFarmHeartRequestDto;
import com.bbogle.yanu.domain.favorite.farm.service.DeleteFarmHeartService;
import com.bbogle.yanu.domain.favorite.farm.service.RegisterFarmHeartService;
import com.bbogle.yanu.domain.favorite.product.dto.DeleteHeartRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites/farms")
public class FavoriteFarmController {
    private final RegisterFarmHeartService registerFarmHeartService;
    private final DeleteFarmHeartService deleteFarmHeartService;

    @PostMapping
    public ResponseEntity<String> registerHeart(@RequestBody RegisterFarmHeartRequestDto request, HttpServletRequest httpRequest){
        registerFarmHeartService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("하트 등록에 성공했습니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHeart(@RequestBody DeleteFarmHeartRequestDto request, HttpServletRequest httpRequest){
        deleteFarmHeartService.execute(request, httpRequest);
        return ResponseEntity.ok().body("하트 삭제 성공했습니다.");
    }

}
