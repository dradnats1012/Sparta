package com.study.sparta.schoolbook.dto;

import java.time.LocalDate;

public record LoanRawDTO(
    Long id,
    String schoolName,
    String bookTitle,
    Integer grade,
    String region,
    String educationOffice,
    LocalDate loanDate
) {
}
