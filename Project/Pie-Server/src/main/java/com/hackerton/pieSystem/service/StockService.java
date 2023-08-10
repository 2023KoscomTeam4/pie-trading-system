package com.hackerton.pieSystem.service;

import java.util.List;

import com.hackerton.pieSystem.component.JsoupComponent;
import com.hackerton.pieSystem.domain.KospiStockDto;
import com.hackerton.pieSystem.domain.StockSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StockService {

    private final JsoupComponent jsoupComponent;

    public List<KospiStockDto> getKosPiStockList() {
        return jsoupComponent.getKosPiStockList();
    }

    private static final String URL = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=";

    public StockSummary getStockSummary(String itemCode) {
        RestTemplate restTemplate = new RestTemplate();
        StockSummary summary = restTemplate.getForObject(URL + itemCode, StockSummary.class);
        return summary;
    }
}