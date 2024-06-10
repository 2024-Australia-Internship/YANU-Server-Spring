package com.bbogle.yanu.domain.sale.controller;

import com.bbogle.yanu.domain.sale.dto.SaleResponseDto;
import com.bbogle.yanu.domain.sale.service.SaleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;
    @GetMapping
    public List<SaleResponseDto> findSale(HttpServletRequest httpRequest){
        return saleService.execute(httpRequest);
    }
}
