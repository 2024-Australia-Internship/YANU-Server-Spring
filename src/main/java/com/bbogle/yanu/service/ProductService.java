package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.product.RegisterProductRequestDto;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.repository.ProductRepository;
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
}
