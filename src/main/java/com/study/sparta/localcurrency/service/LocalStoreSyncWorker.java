package com.study.sparta.localcurrency.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.study.sparta.localcurrency.domain.dto.LocalStoreApiPageResult;
import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalStoreSyncWorker {

    private final AtomicInteger apiCallCount = new AtomicInteger(0);
    private final LocalStoreApiClient apiClient;
    private final LocalStoreSaveService saveService;

    @Async("taskExecutor")
    public CompletableFuture<Void> syncOneAsync(String insttCode, String regionName) {
        log.info("➡ {} 동기화 시작 (insttCode: {})", regionName, insttCode);

        int totalPages = calculateTotalPages(insttCode);
        int page = 1;
        List<LocalStoreDTO> batch = new ArrayList<>();

        while (page <= totalPages) {
            List<LocalStoreDTO> items = fetchPage(insttCode, page);
            if (items.isEmpty()) break;

            batch.addAll(items);

            if (shouldSaveNow(batch.size(), page, totalPages)) {
                saveBatch(batch, regionName, page);
                batch.clear();
            }

            page++;
        }

        log.info("{} 동기화 완료", regionName);
        return CompletableFuture.completedFuture(null);
    }

    private int calculateTotalPages(String insttCode) {
        try {
            LocalStoreApiPageResult result = apiClient.fetchLocalStores(insttCode, 1);
            return (int) Math.ceil(result.getTotalCount() / 1500.0);
        } catch (Exception e) {
            log.error("페이지 수 계산 실패 (insttCode: {})", insttCode, e);
            return 0;
        }
    }

    private List<LocalStoreDTO> fetchPage(String insttCode, int page) {
        try {
            apiCallCount.incrementAndGet();
            LocalStoreApiPageResult result = apiClient.fetchLocalStores(insttCode, page);
            return result.getItems() != null ? result.getItems() : List.of();
        } catch (Exception e) {
            log.error("API 호출 실패 (insttCode: {}, page: {})", insttCode, page, e);
            return List.of();
        }
    }

    private boolean shouldSaveNow(int batchSize, int page, int totalPages) {
        return batchSize >= 10000 || page == totalPages;
    }

    private void saveBatch(List<LocalStoreDTO> batch, String regionName, int page) {
        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            try {
                saveService.saveStores(batch);
                log.info("저장 완료 - region : {}, page : {}", regionName, page);
                success = true;
            } catch (Exception e) {
                attempts++;
                log.warn("저장 실패 - region : {}, page : {}, attempt : {}/3", regionName, page, attempts);

                if (attempts == 3) {
                    log.error("저장 실패 최종 - region : {}, page : {}, 에러 : {}", regionName, page, e.getMessage(), e);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public int getApiCallCount() {
        return apiCallCount.get();
    }
}