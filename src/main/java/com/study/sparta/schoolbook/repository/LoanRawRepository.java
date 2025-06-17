package com.study.sparta.schoolbook.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.sparta.schoolbook.domain.LoanRaw;
import com.study.sparta.schoolbook.dto.LoanRawDTO;

public interface LoanRawRepository extends JpaRepository<LoanRaw, Integer> {

    // TODO : 반환 엔티티로 변경
    List<LoanRawDTO> findByLoanDateBetween(LocalDate start, LocalDate end);
}
