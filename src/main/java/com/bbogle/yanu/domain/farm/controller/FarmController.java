package com.bbogle.yanu.domain.farm.controller;

import com.bbogle.yanu.domain.farm.dto.*;
import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farms")
public class FarmController {
    private final FarmRegisterService farmRegisterService;
    private final FarmImageRegisterService farmImageRegisterService;
    private final FindMyFarmInfoService findMyFarmInfoService;
    private final FindOtherFarmInfoService findOtherFarmInfoService;
    private final FindFarmReviewService findFarmReviewService;
    private final FindListFarmService findListFarmService;

    @PostMapping
    public ResponseEntity<String> registerFarm(@RequestBody RegisterFarmRequestDto request,
                                               HttpServletRequest httpRequest){
        farmRegisterService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("농장 등록 성공했습니다.");
    }

    @PostMapping("/image")
    public ResponseEntity<String> registerFarmImage(@RequestParam("profile")MultipartFile file,
                                                    HttpServletRequest httpRequest) throws IOException {
        farmImageRegisterService.execute(file, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("농장 프로필 등록 성공했습니다");
    }

    @GetMapping
    public ResponseEntity<MyFarmResponseDto> FindMyFarmInfo (HttpServletRequest httpRequest){
        FarmEntity farmEntity = findMyFarmInfoService.execute(httpRequest);
        return ResponseEntity.ok().body(new MyFarmResponseDto(farmEntity));
    }

    @GetMapping("/{user_id}") // id는 userId
    public ResponseEntity<OtherFarmResponseDto> FindOtherFarmInfo (@PathVariable("user_id") Long id,
                                                                   HttpServletRequest httpRequest){
        OtherFarmResponseDto farm = findOtherFarmInfoService.execute(id, httpRequest);
        return ResponseEntity.ok().body(farm);
    }

    @GetMapping("/{farm_id}/reviews")
    public List<FindFarmReviewResponseDto> findFarmReview(@PathVariable("farm_id") Long farmId,
                                                          HttpServletRequest httpRequest){
        List<FindFarmReviewResponseDto> reviews = findFarmReviewService.execute(farmId, httpRequest);
        return reviews;
    }

    @GetMapping("/lists")
    public List<FindListFarmResponseDto> findAllFarm(HttpServletRequest httpRequest){
        return findListFarmService.execute(httpRequest);
    }
}
