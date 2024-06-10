package com.bbogle.yanu.domain.product.controller;

import com.bbogle.yanu.domain.product.dto.*;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.facade.ProductFacade;
import com.bbogle.yanu.domain.product.service.*;
import com.bbogle.yanu.domain.user.facade.UserFacade;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final RegisterProductService registerProductService;
    private final RegisterProductImageService registerProductImageService;
    private final FindAllProductService findAllProductService;
    private final FindAllFarmService findAllFarmService;
    private final FindProductService findProductService;
    private final PutProductService putProductService;
    private final DeleteProductService deleteProductService;

    @PostMapping
    public ResponseEntity<RegisterProductResponseDto> registerProduct(@RequestBody RegisterProductRequestDto request,
                                                                      HttpServletRequest httpRequest){
        RegisterProductResponseDto product = registerProductService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerProductImage(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                                       @RequestParam("productId") Long productId,
                                                       HttpServletRequest httpRequest) throws IOException {
        registerProductImageService.execute(files, productId, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품 이미지 등록 성공했습니다");
    }

    @GetMapping
    public List<ProductAllResponseDto> findAllProduct(HttpServletRequest httpRequest){
        return findAllProductService.execute(httpRequest);
    }

    @GetMapping("farm/{farm_id}")
    public List<ProductFarmResponseDto> findAllFarm(@PathVariable ("farm_id") Long id,
                                                    HttpServletRequest httpRequest){
        return findAllFarmService.execute(id, httpRequest);
    }

    @GetMapping("product/{product_id}")
    public ResponseEntity<ProductResponseDto> findByProduct(@PathVariable ("product_id") Long id,
                                                            HttpServletRequest httpRequest){
        ProductResponseDto product = findProductService.execute(id, httpRequest);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping
    public ResponseEntity<String> putProduct (@RequestBody PutProductRequestDto request,
                                              HttpServletRequest httpRequest){
        putProductService.execute(request, httpRequest);
        return ResponseEntity.ok().body("상품 정보 변경에 성공했습니다");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct (@RequestBody DeleteProductRequestDto request,
                                                 HttpServletRequest httpRequest){
        deleteProductService.execute(request, httpRequest);
        return ResponseEntity.ok().body("상품 삭제에 성공했습니다.");
    }
}
