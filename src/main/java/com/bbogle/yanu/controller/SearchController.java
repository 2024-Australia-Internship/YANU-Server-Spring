package com.bbogle.yanu.controller;

import com.bbogle.yanu.dto.search.SearchResponseDto;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.service.SearchService;
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
}
