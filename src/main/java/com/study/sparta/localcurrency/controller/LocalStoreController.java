package com.study.sparta.localcurrency.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @GetMapping("/search/name")
    public ResponseEntity<Slice<GetLocalStoreMainDTO>> getLocalStoresByStoreName(
        @RequestParam(required = false) String storeName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(localStoreService.getStoresByStoreName(storeName, pageable));
    }

    @GetMapping("/search/region")
    public ResponseEntity<Slice<GetLocalStoreMainDTO>> searchStoresByRegion(
        @RequestParam(required = false) String region,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(localStoreService.getStoresByRegion(region, pageable));
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<GetLocalStoreMainDTO>> getStoresByDistance(
        @RequestParam Double latitude,
        @RequestParam Double longitude,
        @RequestParam int distance
    ) {
        return ResponseEntity.ok(localStoreService.getStoresByDistance(latitude, longitude, distance));
    }

    @GetMapping("/region")
    public ResponseEntity<Slice<GetLocalStoreMainDTO>> getStoresByRegion(
        @RequestParam String region,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(localStoreService.getStoresByRegion(region, pageable));
    }
}
