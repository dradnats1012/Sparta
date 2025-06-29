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
        s.affiliateName, s.localBill, s.ctpvName, s.sggName, s.roadAddr
    )
    FROM LocalStore s
    WHERE (:ctpv IS NULL OR :sgg IS NULL OR (s.ctpvName LIKE %:ctpv% AND s.sggName LIKE %:sgg%))
      AND (:ctpv IS NULL OR s.ctpvName LIKE %:ctpv%)
      AND (:sgg IS NULL OR s.sggName LIKE %:sgg%)
    """)
    Page<GetLocalStoreMainDTO> findByPage(
        String ctpv, String sgg, Pageable pageable
    );

    @Query("""
    SELECT new com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO(
        s.affiliateName, s.localBill, s.ctpvName, s.sggName, s.roadAddr
    )
    FROM LocalStore s
    WHERE (:ctpv IS NULL OR s.ctpvName LIKE %:ctpv%)
      AND (:sgg IS NULL OR s.sggName LIKE %:sgg%)
    ORDER BY s.id
    """)
    List<GetLocalStoreMainDTO> findByOffset(
        String ctpv, String sgg, int size, int offset
    );
}
