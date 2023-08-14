package com.hackerton.pieSystem.controller;


import java.util.List;

import com.hackerton.pieSystem.domain.KospiStock;
import com.hackerton.pieSystem.domain.KospiStockDto;
import com.hackerton.pieSystem.domain.StockDto;
import jakarta.servlet.http.HttpServletRequest;

import com.hackerton.pieSystem.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://54.180.115.36:3000"})
@RequestMapping("/kospi")
public class StockController {

    private final StockService stockService;

    @GetMapping("/stock/{itemCode}")
    public StockDto getStockInfo(@PathVariable String itemCode) {
        return stockService.getStockSummary(itemCode);
    }

    @GetMapping("/all")
    public List<KospiStockDto> getKosPiStockList(HttpServletRequest request) {
        return stockService.getKosPiStockList();
    }

    @GetMapping("/update")
    public void fetchAndUpdateKospiData(HttpServletRequest request) {
        stockService.fetchAndUpdateKospiData();
    }

    @GetMapping("/findall")
    public List<KospiStock> findAll(HttpServletRequest request) {
        return stockService.findAll();
    }
}
