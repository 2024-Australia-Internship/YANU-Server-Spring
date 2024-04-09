package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.farm.FarmUserResponseDto;
import com.bbogle.yanu.dto.farm.RegisterFarmRequestDto;
import com.bbogle.yanu.entity.FarmEntity;
import com.bbogle.yanu.exception.UserNotFoundException;
import com.bbogle.yanu.exception.error.ErrorCode;
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

    public FarmEntity framFindByUser (Long id){
        return farmRepository.findByUserId(id);
    }

}
