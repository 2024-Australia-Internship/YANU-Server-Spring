package com.bbogle.yanu.domain.cart.service;

import com.bbogle.yanu.domain.cart.dto.DeleteCartRequestDto;
import com.bbogle.yanu.domain.cart.repository.CartRepository;
import com.bbogle.yanu.domain.favorite.dto.DeleteHeartRequestDto;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.CartNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCartService {
    private final CartRepository cartRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(DeleteCartRequestDto request, HttpServletRequest httpRequest){
        String token = tokenProvider.resolveToken(httpRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }

        Long userId = tokenProvider.getUserId(token);
        Long productId = request.getProductId().getId();
        boolean exists = cartRepository.existsByUserIdAndProductId(userId, productId);

        if(!exists)
            throw new CartNotFoundException("cart not found", ErrorCode.CART_NOTFOUND);

        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
