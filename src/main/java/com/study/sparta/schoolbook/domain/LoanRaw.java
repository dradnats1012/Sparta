package com.study.sparta.schoolbook.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_raw")
@Getter
@NoArgsConstructor
public class LoanRaw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String region;

    @Column(name = "education_office")
    private String educationOffice;

    @Column(name = "school_name")
    private String schoolName;

    private Integer grade;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "loan_date")
    private LocalDate loanDate;
}