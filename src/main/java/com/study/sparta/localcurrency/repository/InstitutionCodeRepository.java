package com.study.sparta.localcurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.sparta.localcurrency.domain.InstitutionCode;

public interface InstitutionCodeRepository extends JpaRepository<InstitutionCode, Long> {
}
