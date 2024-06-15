package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.dto.DeleteProductRequestDto;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final TokenValidator tokenValidator;

    @Transactional
    public void execute(DeleteProductRequestDto request, HttpServletRequest httpRequest) {
        String token = tokenValidator.validateToken(httpRequest);

        Long productId = request.getProductId();

        boolean productExists = productRepository.existsById(productId);
        if(!productExists)
            throw new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND);

        productImageRepository.deleteByProductId(productId);

        productRepository.deleteById(productId);
    }
}
