package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.product.dto.RegisterProductRequestDto;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterProductService {
    private final ProductRepository productRepository;
    private final FarmRepository farmRepository;
    private final TokenProvider tokenProvider;
    public void execute(RegisterProductRequestDto request, HttpServletRequest httpRequest){
        String token = tokenProvider.resolveToken(httpRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }

        Long userId = tokenProvider.getUserId(token);
        FarmEntity farmEntity = farmRepository.findByUserId(userId);

        productRepository.save(request.toEntity(farmEntity));
    }
}
