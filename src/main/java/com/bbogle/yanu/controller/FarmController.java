package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.farm.FarmUserResponseDto;
import com.bbogle.yanu.dto.farm.RegisterFarmRequestDto;
import com.bbogle.yanu.entity.FarmEntity;
import com.bbogle.yanu.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farms")
public class FarmController {
    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService){
        this.farmService = farmService;
    }

    @PostMapping()
    public ResponseEntity<String> registerFarm(@RequestBody RegisterFarmRequestDto request){
        farmService.registerFarm(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("농장 등록 성공했습니다.");
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<FarmUserResponseDto> FarmFindByUser (@PathVariable("user_id") Long id){
        FarmEntity farmEntity = farmService.framFindByUser(id);
        return ResponseEntity.ok().body(new FarmUserResponseDto(farmEntity));
    }
}
