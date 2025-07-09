package com.study.sparta.localcurrency.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.service.LocalStoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/local-stores")
public class LocalStoreController {

    private final LocalStoreService localStoreService;

    @GetMapping("/name")
    public ResponseEntity<Page<GetLocalStoreMainDTO>> getLocalStoresByStoreName(
        @RequestParam(required = false) String storeName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(localStoreService.getStoresByStoreName(storeName, pageable));
    }

    @GetMapping("/region")
    public ResponseEntity<Page<GetLocalStoreMainDTO>> getStoresByRegion(
        @RequestParam(required = false) String region,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(localStoreService.getStoresByRegion(region, pageable));
    }

    @GetMapping("/nearby")
    public ResponseEntity<Page<GetLocalStoreMainDTO>> getStoresByDistance(
        @RequestParam Double latitude,
        @RequestParam Double longitude,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "30") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(localStoreService.getStoresByDistance(latitude, longitude, pageable));
    }
}
