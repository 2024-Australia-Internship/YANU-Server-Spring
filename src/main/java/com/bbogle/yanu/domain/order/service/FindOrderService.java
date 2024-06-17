package com.bbogle.yanu.domain.order.service;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.order.dto.FindOrderResponseDto;
import com.bbogle.yanu.domain.order.facade.OrderFacade;
import com.bbogle.yanu.domain.order.repository.OrderRepository;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindOrderService {
    private final OrderRepository orderRepository;
    private final ProductImageRepository productImageRepository;
    private final OrderFacade orderFacade;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public List<FindOrderResponseDto> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        List<OrderEntity> orderList = orderRepository.findAllByUserId(userId);

        return orderList.stream()
                .map(order -> {
                    List<ProductImageEntity> images = productImageRepository.findAllByProductId(order.getProduct().getId());
                    boolean isReviewed = orderFacade.checkIsReview(order.getProduct(), userId);
                    return new FindOrderResponseDto(order, isReviewed, images);
                })
                .collect(Collectors.toList());
    }
}
