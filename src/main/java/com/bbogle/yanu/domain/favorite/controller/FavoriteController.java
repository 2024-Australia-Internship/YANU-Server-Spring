package com.bbogle.yanu.domain.favorite.controller;

import com.bbogle.yanu.domain.favorite.dto.DeleteHeartRequestDto;
import com.bbogle.yanu.domain.favorite.dto.FindHeartRequestDto;
import com.bbogle.yanu.domain.favorite.dto.FindHeartResponseDto;
import com.bbogle.yanu.domain.favorite.dto.RegisterHeartRequestDto;
import com.bbogle.yanu.domain.favorite.domain.FavoriteEntity;
import com.bbogle.yanu.domain.favorite.service.FavoriteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<String> registerHeart(@RequestBody RegisterHeartRequestDto request){
        favoriteService.registerHeart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("하트 등록에 성공했습니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHeart(@RequestBody DeleteHeartRequestDto request){
        favoriteService.deleteHeart(request);
        return ResponseEntity.ok().body("하트 삭제 성공했습니다.");
    }

    @GetMapping("/{type}")
    public List<FindHeartResponseDto> findHeart(@PathVariable("type") String type, @RequestBody FindHeartRequestDto request){
        List<FavoriteEntity> hearts = favoriteService.findHeart(type, request);
        return hearts.stream()
                .map(FindHeartResponseDto::new)
                .collect(Collectors.toList());
    }


}
