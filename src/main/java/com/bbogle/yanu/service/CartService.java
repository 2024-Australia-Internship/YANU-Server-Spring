package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.cart.DeleteCartDto;
import com.bbogle.yanu.dto.cart.RegisterCartDto;
import com.bbogle.yanu.entity.CartEntity;
import com.bbogle.yanu.entity.UserEntity;
import com.bbogle.yanu.exception.CartDuplicateException;
import com.bbogle.yanu.exception.CartNotFoundException;
import com.bbogle.yanu.exception.SessionNotFoundException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.CartRepository;
import com.bbogle.yanu.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public void deleteCart(DeleteCartDto request, HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);

        if (session == null)
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);


        Long userId = (Long) session.getAttribute("userId");
        Long productId = request.getProductId().getId();
        boolean exists = cartRepository.existsByUserIdAndProductId(userId, productId);

        if(!exists)
            throw new CartNotFoundException("cart not found", ErrorCode.CART_NOTFOUND);

        UserEntity user = userRepository.findUserById(userId);
        request.setUserId(user);
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public List<CartEntity> findCart(HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession(false);

        if(session == null)
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);

        Long id = (Long) session.getAttribute("userId");

        List<CartEntity> carts = cartRepository.findAllByUserId(id);

        if(carts.isEmpty())
            throw new CartNotFoundException("cart not found", ErrorCode.CART_NOTFOUND);

        return carts;
    }
}
