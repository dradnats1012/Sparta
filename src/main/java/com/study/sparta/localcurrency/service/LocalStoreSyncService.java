package com.study.sparta.localcurrency.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.sparta.localcurrency.domain.InstitutionCode;
import com.study.sparta.localcurrency.repository.InstitutionCodeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalStoreSyncService {

    private final InstitutionCodeRepository institutionCodeRepository;
    private final LocalStoreSyncWorker worker;

    @Transactional
    public void syncAll() {
        log.info("전체 동기화 시작");

        List<InstitutionCode> codes = institutionCodeRepository.findAll();

        List<CompletableFuture<Void>> futures = codes.stream()
            .map(code -> {
                return worker.syncOneAsync(code.getInstitutionCode(), code.getRegionName());
            })
            .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        log.info("전체 API 호출 횟수: {}", worker.getApiCallCount());
        log.info("전체 동기화 완료");
    }
}