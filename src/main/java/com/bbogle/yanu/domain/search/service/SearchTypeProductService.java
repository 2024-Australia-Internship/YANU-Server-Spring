package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.product.facade.ProductFacade;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.domain.search.dto.SearchProductResponseDto;
import com.bbogle.yanu.domain.search.dto.SearchResponseDto;
import com.bbogle.yanu.domain.search.dto.SearchTypeResponseDto;
import com.bbogle.yanu.domain.search.repository.SearchRepository;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
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
public class SearchTypeProductService {
    private final SearchRepository searchRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductFacade productFacade;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public List<SearchResponseDto> execute(String keyword, String type, HttpServletRequest httpRequest) {
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        List<ProductEntity> searchResult = searchRepository.findAllByTitleContainingAndCategory(keyword, type);

        List<Long> productsIds = searchResult.stream().map(ProductEntity::getId).collect(Collectors.toList());
        List<ProductImageEntity> images = productImageRepository.findByProductIdIn(productsIds);

        if (searchResult.isEmpty()) {
            throw new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND);
        }
        return searchResult.stream()
                .map(result ->
                        new SearchResponseDto(result, productFacade.checkIsHeart(result, userId), images))
                .collect(Collectors.toList());
    }
}
