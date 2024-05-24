package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.dto.PutProductRequestDto;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PutProductService {
    private final ProductRepository productRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(PutProductRequestDto request, HttpServletRequest httpRequest){
        String token = tokenProvider.resolveToken(httpRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }

        Long id = request.getProductId();
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));

        product.updateProduct(
                request.getTitle(),
                request.getCategory(),
                request.getHashtag(),
                request.getPrice(),
                request.getUnit(),
                request.getDescription()
        );

        productRepository.save(product);
    }
}
