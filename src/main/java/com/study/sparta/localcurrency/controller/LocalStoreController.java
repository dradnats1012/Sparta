package com.study.sparta.localcurrency.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.service.LocalStoreService;
import com.study.sparta.localcurrency.service.LocalStoreSyncService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/local-store")
public class LocalStoreController {

    private final LocalStoreSyncService syncService;
    private final LocalStoreService localStoreService;

    @PostMapping("/sync")
    public ResponseEntity<String> syncStores() {
        syncService.syncAll();
        return ResponseEntity.ok("동기화 완료");
    }

    @GetMapping("/page")
    public ResponseEntity<Page<GetLocalStoreMainDTO>> getLocalStoresByPage(
        @RequestParam(required = false) String cityName,
        @RequestParam(required = false) String sggName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(localStoreService.getStores(cityName, sggName, pageable));
    }

    @GetMapping("/offset")
    public List<GetLocalStoreMainDTO> getStoresOffset(
        @RequestParam(required = false) String cityName,
        @RequestParam(required = false) String sggName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return localStoreService.getLocalStoresByOffset(cityName, sggName, page, size);
    }
}
