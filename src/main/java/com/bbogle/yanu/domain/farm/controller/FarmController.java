package com.bbogle.yanu.domain.farm.controller;

import com.bbogle.yanu.domain.farm.dto.FarmUserResponseDto;
import com.bbogle.yanu.domain.farm.dto.RegisterFarmRequestDto;
import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.service.FarmRegisterService;
import com.bbogle.yanu.domain.farm.service.FindMyFarmInfoService;
import com.bbogle.yanu.domain.farm.service.FindOtherFarmInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farms")
public class FarmController {
    private final FarmRegisterService farmRegisterService;
    private final FindMyFarmInfoService findMyFarmInfoService;
    private final FindOtherFarmInfoService findOtherFarmInfoService;

    @PostMapping
    public ResponseEntity<String> registerFarm(@RequestBody RegisterFarmRequestDto request, HttpServletRequest httpRequest){
        farmRegisterService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("농장 등록 성공했습니다.");
    }

    @GetMapping
    public ResponseEntity<FarmUserResponseDto> FindMyFarmInfo (HttpServletRequest httpRequest){
        FarmEntity farmEntity = findMyFarmInfoService.execute(httpRequest);
        return ResponseEntity.ok().body(new FarmUserResponseDto(farmEntity));
    }

    @GetMapping("/{id}") // id는 userId
    public ResponseEntity<FarmUserResponseDto> FindOtherFarmInfo (@PathVariable("id") Long id, HttpServletRequest httpRequest){
        FarmEntity farmEntity = findOtherFarmInfoService.execute(id, httpRequest);
        return ResponseEntity.ok().body(new FarmUserResponseDto(farmEntity));
    }
}
