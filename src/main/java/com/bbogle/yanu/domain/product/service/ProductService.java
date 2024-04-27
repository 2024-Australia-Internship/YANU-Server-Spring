package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.dto.RegisterProductRequestDto;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository  productRepository;

    public void registerProduct(RegisterProductRequestDto request){
        productRepository.save(request.toEntity());
    }

    public List<ProductEntity> findAllProduct(){
        return productRepository.findAll();
    }

    public List<ProductEntity> findAllFarm(Long id){
        return productRepository.findAllByFarmId(id);
    }

    public ProductEntity findByProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));
    }
}
