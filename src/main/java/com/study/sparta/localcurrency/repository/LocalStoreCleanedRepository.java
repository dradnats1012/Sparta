package com.study.sparta.localcurrency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
                                    import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

@Repository
public interface LocalStoreCleanedRepository extends JpaRepository<LocalStoreCleaned, Long> {

    @Query(
        value = """
            SELECT * FROM local_store_cleaned
            WHERE MATCH(store_name) AGAINST (:storeName IN NATURAL LANGUAGE MODE)
            """,
        countQuery = """
            SELECT COUNT(*) FROM local_store_cleaned
            WHERE MATCH(store_name) AGAINST (:storeName IN NATURAL LANGUAGE MODE)
            """,
        nativeQuery = true
    )
    Page<LocalStoreCleaned> findAllByStoreName(String storeName, Pageable pageable);

    @Query(
        value = """
            SELECT * FROM local_store_cleaned
            WHERE MATCH(region) AGAINST (:region IN NATURAL LANGUAGE MODE)
            """,
        countQuery = """
            SELECT COUNT(*) FROM local_store_cleaned
            WHERE MATCH(region) AGAINST (:region IN NATURAL LANGUAGE MODE)
            """,
        nativeQuery = true
    )
    Page<LocalStoreCleaned> findAllByRegion(String region, Pageable pageable);

    @Query(
        value = """
                SELECT * FROM local_store_cleaned l 
                 WHERE ST_Distance_Sphere(l.location, ST_SRID(POINT(:longitude, :latitude), 4326)) <= :distance
            """,
        nativeQuery = true
    )
    Page<LocalStoreCleaned> findByDistance(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Integer distance, Pageable pageable);
}
