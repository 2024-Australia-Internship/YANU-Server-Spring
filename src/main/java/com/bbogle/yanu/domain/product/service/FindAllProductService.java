package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.product.dto.ProductAllResponseDto;
import com.bbogle.yanu.domain.product.facade.ProductFacade;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductFacade productFacade;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<ProductAllResponseDto> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        List<ProductEntity> products = productRepository.findAll();
        List<ProductImageEntity> images = productImageRepository.findAll();

        return products.stream()
                .map(product -> new ProductAllResponseDto(product,
                        productFacade.checkIsHeart(product, userId),
                        images))
                .collect(Collectors.toList());
    }
}
