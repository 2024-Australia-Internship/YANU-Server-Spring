package com.bbogle.yanu.domain.product.controller;

import com.bbogle.yanu.domain.product.dto.ProductFarmResponseDto;
import com.bbogle.yanu.domain.product.dto.ProductAllResponseDto;
import com.bbogle.yanu.domain.product.dto.ProductResponseDto;
import com.bbogle.yanu.domain.product.dto.RegisterProductRequestDto;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.service.ProductService;
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
    public List<ProductAllResponseDto> findAllProduct(){
        List<ProductEntity> prodcts = productService.findAllProduct();
        return prodcts.stream()
                .map(ProductAllResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("farm/{farm_id}")
    public List<ProductFarmResponseDto> findAllFarm(@PathVariable ("farm_id") Long id){
        List<ProductEntity> products = productService.findAllFarm(id);
        return products.stream()
                .map(ProductFarmResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("product/{product_id}")
    public ResponseEntity<ProductResponseDto> findByProduct(@PathVariable ("product_id") Long id){
        ProductEntity product = productService.findByProduct(id);
        return ResponseEntity.ok().body(new ProductResponseDto(product));
    }
}
