package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.cart.RegisterCartDto;
import com.bbogle.yanu.dto.favorite.RegisterHeartRequestDto;
import com.bbogle.yanu.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> registerCart(@RequestBody RegisterCartDto requst, HttpServletRequest httpRequest){
        cartService.registerCart(requst, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("장바구니 등록에 성공했습니다.");
    }
}
