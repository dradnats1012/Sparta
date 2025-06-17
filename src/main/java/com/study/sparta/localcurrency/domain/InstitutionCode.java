package com.study.sparta.localcurrency.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "institution_code")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "region_name", nullable = false)
    private String regionName;

    @Column(name = "institution_code", nullable = false)
    private String institutionCode;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
