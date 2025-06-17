package com.study.sparta.localcurrency.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.study.sparta.localcurrency.domain.dto.LocalStoreApiPageResult;
import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;
import com.study.sparta.localcurrency.repository.LocalStoreJdbcRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalStoreSyncWorker {

    private final AtomicInteger apiCallCount = new AtomicInteger(0);
    private final LocalStoreApiClient apiClient;
    private final LocalStoreJdbcRepository jdbcRepository;

    @Async("taskExecutor")
    public CompletableFuture<Void> syncOneAsync(String insttCode, String regionName) {
        log.info("{} 동기화 시작 (insttCode: {})", regionName, insttCode);

        int page = 1;
        int totalPages = 1;
        List<LocalStoreDTO> batch = new ArrayList<>();

        while (true) {
            apiCallCount.incrementAndGet();

            LocalStoreApiPageResult result = apiClient.fetchLocalStores(insttCode, page);

            List<LocalStoreDTO> items = result.getItems();
            if (items == null || items.isEmpty()) {
                log.warn("{} page {} 에서 데이터 없음", regionName, page);
                break;
            }

            if (page == 1) {
                int totalCount = result.getTotalCount();
                totalPages = (int) Math.ceil((double) totalCount / 1000.0);
            }

            batch.addAll(items);

            if (batch.size() >= 15000 || page == totalPages) {
                jdbcRepository.upsertAll(batch);
                log.info("저장 완료 - {} page {}", regionName, page);
                batch.clear();
            }

            page++;
            if (page > totalPages) break;
        }

        log.info("{} 동기화 완료", regionName);
        return CompletableFuture.completedFuture(null);
    }

    public int getApiCallCount() {
        return apiCallCount.get();
    }
}
