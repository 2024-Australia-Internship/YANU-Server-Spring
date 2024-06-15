package com.bbogle.yanu.domain.cart.service;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.cart.dto.FindCartRequestDto;
import com.bbogle.yanu.domain.cart.dto.FindCartResponseDto;
import com.bbogle.yanu.domain.cart.repository.CartRepository;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.global.exception.CartNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindCartService {
    private final CartRepository cartRepository;
    private final ProductImageRepository productImageRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<FindCartResponseDto> execute (HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        List<CartEntity> carts = cartRepository.findAllByUserId(userId);

        if(carts.isEmpty())
            throw new CartNotFoundException("cart not found", ErrorCode.CART_NOTFOUND);

        List<Long> productsIds = carts.stream()
                .map(CartEntity::getProduct)
                .map(product -> product.getId())
                .collect(Collectors.toList());

        List<ProductImageEntity> images = productImageRepository.findByProductIdIn(productsIds);

        Map<Long, List<String>> productImageMap = images.stream()
                .collect(Collectors.groupingBy(
                        image -> image.getProduct().getId(),
                        Collectors.mapping(ProductImageEntity::getUrl, Collectors.toList())
                ));

        return carts.stream()
                .map(cart -> new FindCartResponseDto(
                        cart,
                        productImageMap.getOrDefault(cart.getProduct().getId(), List.of())
                ))
                .collect(Collectors.toList());

    }
}
