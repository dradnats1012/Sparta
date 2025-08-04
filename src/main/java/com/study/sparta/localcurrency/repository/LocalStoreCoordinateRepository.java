package com.study.sparta.localcurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.sparta.localcurrency.domain.LocalStoreCoordinate;

public interface LocalStoreCoordinateRepository extends JpaRepository<LocalStoreCoordinate, Long> {

}
