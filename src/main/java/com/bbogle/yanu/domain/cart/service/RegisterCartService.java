package com.bbogle.yanu.domain.cart.service;

import com.bbogle.yanu.domain.cart.dto.RegisterCartRequestDto;
import com.bbogle.yanu.domain.cart.repository.CartRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.CartDuplicateException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterCartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(RegisterCartRequestDto request, HttpServletRequest httpRequest){
        String token = tokenProvider.resolveToken(httpRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }

        Long userId = tokenProvider.getUserId(token);
        Long productId = request.getProductId().getId();
        boolean exists = cartRepository.existsByUserIdAndProductId(userId, productId);

        if(exists){
            throw new CartDuplicateException("cart duplicated", ErrorCode.CART_DUPLICATION);
        }
        UserEntity user = userRepository.findUserById(userId);
        cartRepository.save(request.toEntity(user));

    }
}
