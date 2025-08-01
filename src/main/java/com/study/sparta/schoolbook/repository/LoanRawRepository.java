package com.study.sparta.schoolbook.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.sparta.schoolbook.domain.LoanRaw;

public interface LoanRawRepository extends JpaRepository<LoanRaw, Long> {

    List<LoanRaw> findByLoanDateBetween(LocalDate start, LocalDate end);
}
