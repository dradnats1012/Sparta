package com.study.sparta.localcurrency.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.study.sparta.localcurrency.domain.LocalStore;
import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;

@Repository
public interface LocalStoreRepository extends JpaRepository<LocalStore, Long> {

    @Query("""
        SELECT new com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO(
            s.storeName, s.localBill, s.cityName, s.sggName, s.roadAddress
        )
        FROM LocalStore s
        WHERE (:cityName IS NULL OR :sgg IS NULL OR (s.cityName LIKE %:cityName% AND s.sggName LIKE %:sggName%))
          AND (:cityName IS NULL OR s.cityName LIKE %:cityName%)
          AND (:sggName IS NULL OR s.sggName LIKE %:sggName%)
        """)
    Page<GetLocalStoreMainDTO> findByPage(
        String cityName, String sggName, Pageable pageable
    );

    @Query("""
        SELECT new com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO(
            s.storeName, s.localBill, s.cityName, s.sggName, s.roadAddress
        )
        FROM LocalStore s
        WHERE (:cityName IS NULL OR s.cityName LIKE %:cityName%)
          AND (:sggName IS NULL OR s.sggName LIKE %:sggName%)
        ORDER BY s.id
        """)
    List<GetLocalStoreMainDTO> findByOffset(
        String cityName, String sggName, int size, int offset
    );
}
