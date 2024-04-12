package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.farm.FarmUserResponseDto;
import com.bbogle.yanu.dto.farm.RegisterFarmRequestDto;
import com.bbogle.yanu.entity.FarmEntity;
import com.bbogle.yanu.service.FarmService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farms")
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<String> registerFarm(@RequestBody RegisterFarmRequestDto request, HttpServletRequest httpRequest){
        farmService.registerFarm(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("농장 등록 성공했습니다.");
    }

    @GetMapping
    public ResponseEntity<FarmUserResponseDto> FarmFindById (HttpServletRequest httpRequest){
        FarmEntity farmEntity = farmService.farmFindById(httpRequest);
        return ResponseEntity.ok().body(new FarmUserResponseDto(farmEntity));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<FarmUserResponseDto> FarmFindByUser (@PathVariable("user_id") Long id){
        FarmEntity farmEntity = farmService.framFindByUser(id);
        return ResponseEntity.ok().body(new FarmUserResponseDto(farmEntity));
    }
}
