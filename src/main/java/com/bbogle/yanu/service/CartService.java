package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.cart.RegisterCartDto;
import com.bbogle.yanu.entity.UserEntity;
import com.bbogle.yanu.exception.CartDuplicateException;
import com.bbogle.yanu.exception.SessionNotFoundException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.CartRepository;
import com.bbogle.yanu.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public void registerCart(RegisterCartDto request, HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession(false);

        if(session == null){
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);
        }

        Long userId = (Long) session.getAttribute("userId");
        Long productId = request.getProductId().getId();
        boolean exists = cartRepository.existsByUserIdAndProductId(userId, productId);

        if(exists){
            throw new CartDuplicateException("cart duplicated", ErrorCode.CART_DUPLICATION);
        }

        UserEntity user = userRepository.findUserById(userId);
        request.setUserId(user);
        cartRepository.save(request.toEntity());
    }

}
