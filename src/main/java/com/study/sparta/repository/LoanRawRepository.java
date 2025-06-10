package com.study.sparta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.study.sparta.domain.LoanRaw;
import com.study.sparta.dto.LoanRawDTO;

public interface LoanRawRepository extends Repository<LoanRaw, Integer> {

    List<LoanRaw> findByLoanDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("""
            SELECT new com.study.sparta.dto.LoanRawDTO(l.schoolName, l.bookTitle, l.grade, l.loanDate)
            FROM LoanRaw l
            WHERE l.loanDate BETWEEN :start AND :end
        """)
    List<LoanRawDTO> findDTOByLoanDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

}
