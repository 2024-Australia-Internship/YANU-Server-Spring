package com.bbogle.yanu.domain.favorite.product.controller;

import com.bbogle.yanu.domain.favorite.product.dto.DeleteHeartRequestDto;
import com.bbogle.yanu.domain.favorite.product.dto.FindHeartRequestDto;
import com.bbogle.yanu.domain.favorite.product.dto.FindHeartResponseDto;
import com.bbogle.yanu.domain.favorite.product.dto.RegisterHeartRequestDto;
import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import com.bbogle.yanu.domain.favorite.product.service.DeleteHeartService;
import com.bbogle.yanu.domain.favorite.product.service.FindHeartService;
import com.bbogle.yanu.domain.favorite.product.service.RegisterHeartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites/products")
public class FavoriteProductController {
    private final RegisterHeartService registerHeartService;
    private final DeleteHeartService deleteHeartService;
    private final FindHeartService findHeartService;

    @PostMapping
    public ResponseEntity<String> registerHeart(@RequestBody RegisterHeartRequestDto request, HttpServletRequest httpRequest){
        registerHeartService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("하트 등록에 성공했습니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHeart(@RequestBody DeleteHeartRequestDto request, HttpServletRequest httpRequest){
        deleteHeartService.execute(request, httpRequest);
        return ResponseEntity.ok().body("하트 삭제 성공했습니다.");
    }

    @GetMapping
    public List<FindHeartResponseDto> findHeart(@RequestBody FindHeartRequestDto request, HttpServletRequest httpRequest){
        List<FavoriteProductEntity> hearts = findHeartService.execute(request, httpRequest);
        return hearts.stream()
                .map(FindHeartResponseDto::new)
                .collect(Collectors.toList());
    }


}
