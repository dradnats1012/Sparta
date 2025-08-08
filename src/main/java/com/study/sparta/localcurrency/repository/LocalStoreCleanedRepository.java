package com.study.sparta.localcurrency.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;

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

    @Query(value = """
        SELECT lc.*
        FROM local_store_coordinate lsc
        JOIN local_store_cleaned lc ON lsc.cleaned_id = lc.id
        WHERE ST_Contains(
            ST_Buffer(
                ST_GeomFromText(CONCAT('POINT(', :latitude, ' ', :longitude, ')'), 4326),
                :distance
            ),
            lsc.location
        )
        """, nativeQuery = true)
    List<LocalStoreCleaned> findStoresWithinDistanceByBuffer(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude,
        @Param("distance") int distance
    );

    Optional<LocalStoreCleaned> findByUuid(UUID uuid);

    default LocalStoreCleaned getByUuid(UUID uuid) {
        return findByUuid(uuid)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상점의 id입니다!"));
    }

    @Query(value = """
        SELECT lc.*
        FROM local_store_coordinate lsc
        JOIN local_store_cleaned lc ON lsc.cleaned_id = lc.id
            WHERE MBRContains(
                ST_GeomFromText(
                    CONCAT(
                        'LINESTRING(', :leftLatitude, ' ', :leftLongitude, ',', :rightLatitude, ' ', :rightLongitude,')'
                    ), 4326), 
                lsc.location)
        """, nativeQuery = true)
    List<LocalStoreCleaned> findStoresInBoundingBox(
        @Param("leftLatitude") Double leftLatitude,
        @Param("leftLongitude") Double leftLongitude,
        @Param("rightLatitude") Double rightLatitude,
        @Param("rightLongitude") Double rightLongitude
    );
}
