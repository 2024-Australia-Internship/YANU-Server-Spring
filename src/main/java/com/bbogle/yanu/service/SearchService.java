package com.bbogle.yanu.service;

import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.exception.ProductNotFoundException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final ServiceRepository serviceRepository;

    public List<ProductEntity> searchProduct(String keyword){
        List<ProductEntity> searchResult = serviceRepository.findAllByTitleContaining(keyword);
        if (searchResult.isEmpty()) {
            throw new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND);
        }
        return searchResult;
    }
}
