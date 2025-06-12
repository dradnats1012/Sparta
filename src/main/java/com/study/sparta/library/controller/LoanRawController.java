package com.study.sparta.library.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.library.dto.LoanRawDTO;
import com.study.sparta.library.service.LoanRawService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loanRaw")
@RequiredArgsConstructor
public class LoanRawController {

    private final LoanRawService loanRawService;

    @GetMapping
    public ResponseEntity<List<LoanRawDTO>> getLoanRaws(
        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        long start = System.currentTimeMillis();

        List<LoanRawDTO> records = loanRawService.getLoanRawDto(startDate, endDate);

        long end = System.currentTimeMillis();
        System.out.println("조회 시간 : " + (end - start) + "ms");

        return ResponseEntity.ok(records);
    }
}

