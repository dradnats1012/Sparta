package com.study.sparta.localcurrency.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

@Repository
public interface LocalStoreCleanedRepository extends JpaRepository<LocalStoreCleaned, Long> {

    @Query(
        value = """
            SELECT * FROM local_store_cleaned
            WHERE MATCH(store_name) AGAINST (:storeName IN NATURAL LANGUAGE MODE)
            """,
        nativeQuery = true
    )
    Slice<LocalStoreCleaned> findAllByStoreName(String storeName, Pageable pageable);

    @Query(
        value = """
            SELECT * FROM local_store_cleaned
            WHERE MATCH(region) AGAINST (:region IN NATURAL LANGUAGE MODE)
            """,
        nativeQuery = true
    )
    Slice<LocalStoreCleaned> findAllByRegion(String region, Pageable pageable);
}
