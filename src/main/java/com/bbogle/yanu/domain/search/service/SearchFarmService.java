package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.search.dto.SearchFarmResponseDto;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchFarmService {
    private final FarmRepository farmRepository;
    private final ProductRepository productRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public List<SearchFarmResponseDto> execute(String keyword, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        FarmEntity farm = farmRepository.findByUserId(userId);
        Long farmId = farm.getId();

        List<FarmEntity> searchResult = farmRepository.findAllByBusinessNameContaining(keyword);

        return searchResult.stream()
                .map(result -> new SearchFarmResponseDto(result))
                .collect(Collectors.toList());
    }

    private int cntProduct(Long farmId){
        return productRepository.countByFarmId(farmId);
    }
}
