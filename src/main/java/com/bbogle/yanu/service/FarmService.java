package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.farm.RegisterFarmRequestDto;
import com.bbogle.yanu.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FarmService {
    private final FarmRepository farmRepository;

    public void registerFarm(RegisterFarmRequestDto request){
        farmRepository.save(request.toEntity());
    }
}
