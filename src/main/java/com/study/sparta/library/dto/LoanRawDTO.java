package com.study.sparta.library.dto;

import java.time.LocalDate;

public record LoanRawDTO(
    Integer id,
    String schoolName,
    String bookTitle,
    Integer grade,
    String region,
    String educationOffice,
    LocalDate loanDate
) {
}