package com.hackerton.pieSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.hackerton.pieSystem.component.JsoupComponent;
import com.hackerton.pieSystem.domain.*;
import com.hackerton.pieSystem.repository.KospiStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StockService {

    @Autowired
    private KospiStockRepository kospiStockRepository;

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

    public void fetchAndUpdateKospiData() {
        List<KospiStockDto> kospiDataArray = getKosPiStockList();
        if (kospiDataArray != null) {
            for (KospiStockDto kospiData : kospiDataArray) {
                KospiStock kospi = new KospiStock();
                kospi.setId(kospiData.getNo());
                kospi.setNo(kospiData.getDiscussionRoomUrl().replace("https://finance.naver.comhttps://finance.naver.com/item/board.naver?code=", ""));
                kospi.setStockName(kospiData.getStockName());
                kospi.setPrice(kospiData.getPrice());
                kospiStockRepository.save(kospi); // 데이터 저장 또는 업데이트
            }
        }
    }
    
    @Transactional(readOnly = true)
    public List<KospiStock> findAll() {
        return kospiStockRepository.findAll();
    }
}