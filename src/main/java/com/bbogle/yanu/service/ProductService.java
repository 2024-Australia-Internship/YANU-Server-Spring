package com.bbogle.yanu.service;

import com.bbogle.yanu.controller.ProductController;
import com.bbogle.yanu.dto.product.RegisterProductRequestDto;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.exception.ProductNotFoundException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
