package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.farm.RegisterFarmRequestDto;
import com.bbogle.yanu.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
