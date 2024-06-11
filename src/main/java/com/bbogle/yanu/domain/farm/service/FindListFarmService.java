package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.FindListFarmResponseDto;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindListFarmService {
    private final FarmRepository farmRepository;
    private final TokenValidator tokenValidator;

    @Transactional(readOnly = true)
    public List<FindListFarmResponseDto> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        List<FarmEntity> farms = farmRepository.findAll();

        return farms.stream()
                .map(FindListFarmResponseDto::new)
                .collect(Collectors.toList());
    }
}
