package com.study.sparta.schoolbook.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.schoolbook.dto.LoanRawDTO;
import com.study.sparta.schoolbook.service.LoanRawService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoanRawController {

    private final LoanRawService loanRawService;

    @GetMapping
    public ResponseEntity<List<LoanRawDTO>> getLoanRaws(
        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<LoanRawDTO> records = loanRawService.getLoanRawDto(startDate, endDate);
        return ResponseEntity.ok(records);
    }
}
