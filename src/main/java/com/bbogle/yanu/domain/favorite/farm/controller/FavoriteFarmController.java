package com.bbogle.yanu.domain.favorite.farm.controller;

import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import com.bbogle.yanu.domain.favorite.farm.dto.DeleteFarmHeartRequestDto;
import com.bbogle.yanu.domain.favorite.farm.dto.FindFarmHeartResponseDto;
import com.bbogle.yanu.domain.favorite.farm.dto.RegisterFarmHeartRequestDto;
import com.bbogle.yanu.domain.favorite.farm.service.DeleteFarmHeartService;
import com.bbogle.yanu.domain.favorite.farm.service.FindFarmHeartService;
import com.bbogle.yanu.domain.favorite.farm.service.RegisterFarmHeartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites/farms")
public class FavoriteFarmController {
    private final RegisterFarmHeartService registerFarmHeartService;
    private final DeleteFarmHeartService deleteFarmHeartService;
    private final FindFarmHeartService findFarmHeartService;

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

    @GetMapping
    public List<FindFarmHeartResponseDto> findHeart(HttpServletRequest httpRequest){
        List<FavoriteFarmEntity> hearts = findFarmHeartService.execute(httpRequest);
        return hearts.stream()
                .map(FindFarmHeartResponseDto::new)
                .collect(Collectors.toList());
    }
}
