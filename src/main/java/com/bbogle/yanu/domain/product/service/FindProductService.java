package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindProductService {
    private final ProductRepository productRepository;
    private final TokenProvider tokenProvider;

    public ProductEntity execute(Long product_id, HttpServletRequest httpRequest){
        String token = tokenProvider.resolveToken(httpRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }

        return productRepository.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));

    }
}
