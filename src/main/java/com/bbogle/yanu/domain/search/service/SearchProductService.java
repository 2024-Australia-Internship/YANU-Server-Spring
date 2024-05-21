package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.search.repository.SearchRepository;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchProductService {
    private final SearchRepository searchRepository;
    private final TokenProvider tokenProvider;

    public List<ProductEntity> execute(String keyword, HttpServletRequest httpRequest){
        String token = tokenProvider.resolveToken(httpRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }

        List<ProductEntity> searchResult = searchRepository.findAllByTitleContaining(keyword);
        if (searchResult.isEmpty()) {
            throw new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND);
        }
        return searchResult;

    }
}
