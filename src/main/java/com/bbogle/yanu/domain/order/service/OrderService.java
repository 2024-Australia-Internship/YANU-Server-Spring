package com.bbogle.yanu.domain.order.service;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.order.dto.OrderRequestDto;
import com.bbogle.yanu.domain.order.repository.OrderRepository;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.UserNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(OrderRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("user not found", ErrorCode.USER_NOTFOUND));

        List<OrderEntity> orderList = request.toEntity(user);
        for(OrderEntity order : orderList) {
            productRepository.findById(order.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));

            orderRepository.save(order);
        }
    }
}