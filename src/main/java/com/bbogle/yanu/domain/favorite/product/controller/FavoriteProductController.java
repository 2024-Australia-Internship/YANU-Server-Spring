package com.bbogle.yanu.domain.favorite.product.controller;

import com.bbogle.yanu.domain.favorite.product.dto.DeleteProductHeartRequestDto;
import com.bbogle.yanu.domain.favorite.product.dto.FindProductHeartResponseDto;
import com.bbogle.yanu.domain.favorite.product.dto.RegisterProductHeartRequestDto;
import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import com.bbogle.yanu.domain.favorite.product.service.DeleteProductHeartService;
import com.bbogle.yanu.domain.favorite.product.service.FindProductHeartService;
import com.bbogle.yanu.domain.favorite.product.service.RegisterProductHeartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites/products")
public class FavoriteProductController {
    private final RegisterProductHeartService registerProductHeartService;
    private final DeleteProductHeartService deleteProductHeartService;
    private final FindProductHeartService findProductHeartService;

    @PostMapping
    public ResponseEntity<String> registerHeart(@RequestBody RegisterProductHeartRequestDto request,
                                                HttpServletRequest httpRequest){
        registerProductHeartService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("하트 등록에 성공했습니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHeart(@RequestBody DeleteProductHeartRequestDto request,
                                              HttpServletRequest httpRequest){
        deleteProductHeartService.execute(request, httpRequest);
        return ResponseEntity.ok().body("하트 삭제 성공했습니다.");
    }

    @GetMapping
    public List<FindProductHeartResponseDto> findHeart(HttpServletRequest httpRequest){
        return findProductHeartService.execute(httpRequest);
    }


}
