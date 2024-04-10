package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.product.ProductResponseDto;
import com.bbogle.yanu.dto.product.RegisterProductRequestDto;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> registerProduct(@RequestBody RegisterProductRequestDto request){
        productService.registerProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품 등록 성공했습니다");
    }

    @GetMapping
    public List<ProductResponseDto> findAllProduct(){
        List<ProductEntity> prodcts = productService.findAllProduct();
        return prodcts.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

}
