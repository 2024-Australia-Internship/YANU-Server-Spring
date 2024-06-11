package com.bbogle.yanu.domain.order.controller;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.order.dto.FindOrderResponseDto;
import com.bbogle.yanu.domain.order.dto.OrderRequestDto;
import com.bbogle.yanu.domain.order.dto.OrderResponseDto;
import com.bbogle.yanu.domain.order.service.FindOrderService;
import com.bbogle.yanu.domain.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final FindOrderService findOrderService;

    @PostMapping
    public List<OrderResponseDto> order(@RequestBody OrderRequestDto request,
                                                  HttpServletRequest httpRequest){

        return orderService.execute(request, httpRequest);
    }

    @GetMapping
    public List<FindOrderResponseDto> findOrder(HttpServletRequest httpRequest){
        return findOrderService.execute(httpRequest);
    }
}
