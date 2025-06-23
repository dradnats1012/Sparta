package com.study.sparta.schoolbook.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.sparta.schoolbook.domain.LoanRaw;
import com.study.sparta.schoolbook.dto.LoanRawDTO;
import com.study.sparta.schoolbook.repository.LoanRawRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanRawService {

    private final LoanRawRepository loanRawRepository;

    public List<LoanRawDTO> getLoanRawDto(LocalDate startDate, LocalDate endDate) {
        List<LoanRaw> loans = loanRawRepository.findByLoanDateBetween(startDate, endDate);
        return loans.stream()
            .map(loan -> new LoanRawDTO(
                loan.getId(),
                loan.getSchoolName(),
                loan.getBookTitle(),
                loan.getGrade(),
                loan.getRegion(),
                loan.getEducationOffice(),
                loan.getLoanDate()
            )).toList();
    }
}