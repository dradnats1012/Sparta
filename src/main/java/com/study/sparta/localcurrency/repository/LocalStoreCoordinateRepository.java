package com.study.sparta.localcurrency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.LocalStoreCoordinate;

public interface LocalStoreCoordinateRepository extends JpaRepository<LocalStoreCoordinate, Long> {


}
