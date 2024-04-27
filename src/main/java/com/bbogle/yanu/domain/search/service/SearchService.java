package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.domain.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository serviceRepository;

    public List<ProductEntity> searchProduct(String keyword){
        List<ProductEntity> searchResult = serviceRepository.findAllByTitleContaining(keyword);
        if (searchResult.isEmpty()) {
            throw new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND);
        }
        return searchResult;
    }

    public List<ProductEntity> searchTypeProduct(String keyword, String type){
        List<ProductEntity> searchResult = serviceRepository.findAllByTitleContainingAndCategory(keyword, type);
        if (searchResult.isEmpty()) {
            throw new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND);
        }
        return searchResult;
    }
}
