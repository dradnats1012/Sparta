/*
package com.study.sparta.localcurrency.controller;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.service.ShardFanoutService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shards")
public class ShardController {
    private final ShardFanoutService svc;

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        return ResponseEntity.ok(Map.of("total_cleaned", svc.totalCleanedCount()));
    }

    @GetMapping("/search")
    public ResponseEntity<Slice<GetLocalStoreMainDTO>> search(
        @RequestParam String q,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "200") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(svc.searchStores(q, pageable));
    }
}
*/
