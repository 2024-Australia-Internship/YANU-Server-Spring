package com.bbogle.yanu.domain.product.controller;

import com.bbogle.yanu.domain.product.dto.*;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.service.*;
import jakarta.servlet.http.HttpServletRequest;
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
    private final RegisterProductService registerProductService;
    private final FindAllProductService findAllProductService;
    private final FindAllFarmService findAllFarmService;
    private final FindProductService findProductService;
    private final PutProductService putProductService;
    private final DeleteProductService deleteProductService;

    @PostMapping
    public ResponseEntity<String> registerProduct(@RequestBody RegisterProductRequestDto request, HttpServletRequest httpRequest){
        registerProductService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품 등록 성공했습니다");
    }

    @GetMapping
    public List<ProductAllResponseDto> findAllProduct(HttpServletRequest httpRequest){
        List<ProductEntity> prodcts = findAllProductService.execute(httpRequest);
        return prodcts.stream()
                .map(ProductAllResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("farm/{farm_id}")
    public List<ProductFarmResponseDto> findAllFarm(@PathVariable ("farm_id") Long id, HttpServletRequest httpRequest){
        List<ProductEntity> products = findAllFarmService.execute(id, httpRequest);
        return products.stream()
                .map(ProductFarmResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("product/{product_id}")
    public ResponseEntity<ProductResponseDto> findByProduct(@PathVariable ("product_id") Long id, HttpServletRequest httpRequest){
        ProductEntity product = findProductService.execute(id, httpRequest);
        return ResponseEntity.ok().body(new ProductResponseDto(product));
    }

    @PutMapping
    public ResponseEntity<String> putProduct (@RequestBody PutProductRequestDto request, HttpServletRequest httpRequest){
        putProductService.execute(request, httpRequest);
        return ResponseEntity.ok().body("상품 정보 변경에 성공했습니다");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct (@RequestBody DeleteProductRequestDto request, HttpServletRequest httpRequest){
        deleteProductService.execute(request, httpRequest);
        return ResponseEntity.ok().body("상품 삭제에 성공했습니다.");
    }
}
