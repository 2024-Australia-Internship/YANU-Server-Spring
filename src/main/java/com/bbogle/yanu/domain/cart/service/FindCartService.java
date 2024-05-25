package com.bbogle.yanu.domain.cart.service;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.cart.dto.FindCartRequestDto;
import com.bbogle.yanu.domain.cart.repository.CartRepository;
import com.bbogle.yanu.global.exception.CartNotFoundException;
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
public class FindCartService {
    private final CartRepository cartRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<CartEntity> execute (HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        List<CartEntity> carts = cartRepository.findAllByUserId(userId);

        if(carts.isEmpty())
            throw new CartNotFoundException("cart not found", ErrorCode.CART_NOTFOUND);

        return carts;

    }
}
