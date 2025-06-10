package com.study.sparta.dto;

import java.time.LocalDate;

public record LoanRawDTO(
    String schoolName,
    String bookTitle,
    Integer grade,
    LocalDate loanDate
) {
}
