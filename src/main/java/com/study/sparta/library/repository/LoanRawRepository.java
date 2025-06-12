package com.study.sparta.library.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.sparta.library.domain.LoanRaw;
import com.study.sparta.library.dto.LoanRawDTO;

public interface LoanRawRepository extends JpaRepository<LoanRaw, Integer> {

    List<LoanRawDTO> findByLoanDateBetween(LocalDate start, LocalDate end);
}
