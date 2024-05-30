package com.bbogle.yanu.domain.cart.service;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.cart.dto.UpdateQuantityRequestDto;
import com.bbogle.yanu.domain.cart.repository.CartRepository;
import com.bbogle.yanu.global.exception.CartNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateQuantityService {
    private final CartRepository cartRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(UpdateQuantityRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        Long productId = request.getProductId().getId();
        int quantity = request.getQuantity();

        CartEntity cart = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new CartNotFoundException("cart not found", ErrorCode.CART_NOTFOUND));

        cart.updateQuantity(quantity);

        cartRepository.save(cart);
    }
}
