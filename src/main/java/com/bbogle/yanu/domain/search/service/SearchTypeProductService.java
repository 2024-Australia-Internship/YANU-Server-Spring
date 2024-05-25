package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
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

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchTypeProductService {
    private final SearchRepository searchRepository;
    private final TokenValidator tokenValidator;

    public List<ProductEntity> execute(String keyword, String type, HttpServletRequest httpRequest) {
        String token = tokenValidator.validateToken(httpRequest);

        List<ProductEntity> searchResult = searchRepository.findAllByTitleContaining(keyword);
        if (searchResult.isEmpty()) {
            throw new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND);
        }
        return searchResult;
    }
}
