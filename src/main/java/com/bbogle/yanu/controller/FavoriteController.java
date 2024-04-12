package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.favorite.DeleteHeartRequestDto;
import com.bbogle.yanu.dto.favorite.FindHeartResponseDto;
import com.bbogle.yanu.dto.favorite.RegisterHeartRequestDto;
import com.bbogle.yanu.entity.FavoriteEntity;
import com.bbogle.yanu.service.FavoriteService;
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
    public ResponseEntity<String> registerHeart(@RequestBody RegisterHeartRequestDto request, HttpServletRequest httpRequest){
        favoriteService.registerHeart(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("하트 등록에 성공했습니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHeart(@RequestBody DeleteHeartRequestDto request){
        favoriteService.deleteHeart(request);
        return ResponseEntity.ok().body("하트 삭제 성공했습니다.");
    }

    @GetMapping("/{type}/{user_id}")
    public List<FindHeartResponseDto> findHeart(@PathVariable("type") String type, @PathVariable("user_id") Long id){
        List<FavoriteEntity> hearts = favoriteService.findHeart(type, id);
        return hearts.stream()
                .map(FindHeartResponseDto::new)
                .collect(Collectors.toList());
    }


}
