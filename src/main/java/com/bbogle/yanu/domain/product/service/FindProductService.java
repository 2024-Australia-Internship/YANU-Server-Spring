package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.dto.ProductAllResponseDto;
import com.bbogle.yanu.domain.product.dto.ProductResponseDto;
import com.bbogle.yanu.domain.product.facade.ProductFacade;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
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
public class FindProductService {
    private final ProductRepository productRepository;
    private final TokenValidator tokenValidator;
    private final ProductFacade productFacade;
    private final TokenProvider tokenProvider;

    public ProductResponseDto execute(Long product_id, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        ProductEntity product = productRepository.findById(product_id)
                .orElseThrow(()-> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));

        return new ProductResponseDto(product, productFacade.checkIsHeart(product, userId));
    }
}
