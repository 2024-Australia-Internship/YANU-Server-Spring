package com.bbogle.yanu.domain.search.controller;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.search.dto.SearchFarmResponseDto;
import com.bbogle.yanu.domain.search.dto.SearchProductResponseDto;
import com.bbogle.yanu.domain.search.dto.SearchResponseDto;
import com.bbogle.yanu.domain.search.dto.SearchTypeResponseDto;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.search.service.SearchFarmService;
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
    private final SearchFarmService searchFarmService;

    @GetMapping("/{keyword}")
    public List<SearchProductResponseDto> searchProduct(@PathVariable ("keyword") String keyword,
                                                        HttpServletRequest httpRequest){
        List<SearchProductResponseDto> products = searchProductService.execute(keyword, httpRequest);
        return products;
    }

    @GetMapping("/{keyword}/{type}")
    public List<SearchResponseDto> searchTypeProduct(@PathVariable ("keyword") String keyword, @PathVariable("type") String type,
                                                     HttpServletRequest httpRequest){
        List<SearchResponseDto> products = searchTypeProductService.execute(keyword, type, httpRequest);
        return products;
    }

    @GetMapping("/farms/{keyword}")
    public List<SearchFarmResponseDto> searchFarm(@PathVariable("keyword") String keyword,
                                                  HttpServletRequest httpRequest){
         return searchFarmService.execute(keyword, httpRequest);
    }
}
