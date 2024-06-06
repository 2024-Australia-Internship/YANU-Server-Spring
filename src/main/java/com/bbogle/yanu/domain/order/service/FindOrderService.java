package com.bbogle.yanu.domain.order.service;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.order.dto.FindOrderResponseDto;
import com.bbogle.yanu.domain.order.facade.OrderFacade;
import com.bbogle.yanu.domain.order.repository.OrderRepository;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindOrderService {
    private final OrderRepository orderRepository;
    private final OrderFacade orderFacade;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<FindOrderResponseDto> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        List<OrderEntity> orderList = orderRepository.findAllByUserId(userId);

        return orderList.stream()
                .map(order -> new FindOrderResponseDto(order, orderFacade.checkIsReview(order.getProduct(), userId)))
                .collect(Collectors.toList());
    }
}
