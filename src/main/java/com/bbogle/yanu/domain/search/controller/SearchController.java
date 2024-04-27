package com.bbogle.yanu.domain.search.controller;

import com.bbogle.yanu.domain.search.dto.SearchResponseDto;
import com.bbogle.yanu.domain.search.dto.SearchTypeResponseDto;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/searches")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/{keyword}")
    public List<SearchResponseDto> searchProduct(@PathVariable ("keyword") String keyword){
        List<ProductEntity> products = searchService.searchProduct(keyword);
        return products.stream()
                .map(SearchResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{keyword}/{type}")
    public List<SearchTypeResponseDto> searchTypeProduct(@PathVariable ("keyword") String keyword, @PathVariable("type") String type){
        List<ProductEntity> products = searchService.searchTypeProduct(keyword, type);
        return products.stream()
                .map(SearchTypeResponseDto::new)
                .collect(Collectors.toList());
    }
}
