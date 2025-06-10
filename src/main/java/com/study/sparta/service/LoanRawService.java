package com.study.sparta.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.sparta.dto.LoanRawDTO;
import com.study.sparta.repository.LoanRawRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanRawService {

    private final LoanRawRepository loanRawRepository;

    public List<LoanRawDTO> getLoanRawDto(LocalDate startDate, LocalDate endDate) {
        return loanRawRepository.findDTOByLoanDateBetween(startDate, endDate);
    }
}