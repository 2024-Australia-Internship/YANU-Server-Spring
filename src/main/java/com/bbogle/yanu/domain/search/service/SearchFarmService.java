package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchFarmService {
    private final FarmRepository farmRepository;
    private final TokenValidator tokenValidator;

    public List<FarmEntity> execute(String keyword, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        List<FarmEntity> searchResult = farmRepository.findAllByBusinessNameContaining(keyword);

        return searchResult;
    }
}
