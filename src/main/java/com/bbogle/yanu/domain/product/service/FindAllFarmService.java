package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.dto.ProductAllResponseDto;
import com.bbogle.yanu.domain.product.dto.ProductFarmResponseDto;
import com.bbogle.yanu.domain.product.facade.ProductFacade;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllFarmService {
    private final ProductRepository productRepository;
    private final ProductFacade productFacade;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<ProductFarmResponseDto> execute(Long farmId, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId =  tokenProvider.getUserId(token);

        List<ProductEntity> products = productRepository.findAllByFarmId(farmId);

        return products.stream()
                .map(product -> new ProductFarmResponseDto(product, productFacade.checkIsHeart(product, userId)))
                .collect(Collectors.toList());
    }
}
