package com.bbogle.yanu.domain.search.controller;

import com.bbogle.yanu.domain.search.dto.SearchResponseDto;
import com.bbogle.yanu.domain.search.dto.SearchTypeResponseDto;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.search.service.SearchProductService;
import com.bbogle.yanu.domain.search.service.SearchTypeProductService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final SearchProductService searchProductService;
    private final SearchTypeProductService searchTypeProductService;

    @GetMapping("/{keyword}")
    public List<SearchResponseDto> searchProduct(@PathVariable ("keyword") String keyword, HttpServletRequest httpRequest){
        List<ProductEntity> products = searchProductService.execute(keyword, httpRequest);
        return products.stream()
                .map(SearchResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{keyword}/{type}")
    public List<SearchTypeResponseDto> searchTypeProduct(@PathVariable ("keyword") String keyword, @PathVariable("type") String type, HttpServletRequest httpRequest){
        List<ProductEntity> products = searchTypeProductService.execute(keyword, type, httpRequest);
        return products.stream()
                .map(SearchTypeResponseDto::new)
                .collect(Collectors.toList());
    }
}
