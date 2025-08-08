package com.study.sparta.localcurrency.domain;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "institution_code")
@Getter
@NoArgsConstructor
public class InstitutionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_name", nullable = false)
    private String regionName;

    @Column(name = "code", nullable = false)
    private String code;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @JdbcTypeCode(Types.BINARY)
    @Column(name = "uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID uuid;
}
