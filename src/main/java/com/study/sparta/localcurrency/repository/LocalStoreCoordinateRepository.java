package com.study.sparta.localcurrency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.LocalStoreCoordinate;

public interface LocalStoreCoordinateRepository extends JpaRepository<LocalStoreCoordinate, Long> {

    @Query(
        value = """
                SELECT * FROM local_store_coordinate l 
                 WHERE MBRContains(ST_BUFFER(ST_SRID(POINT(:longitude, :latitude), 4326), :distance), location) 
            """,
        nativeQuery = true
    )
    List<LocalStoreCoordinate> findByDistance(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Integer distance);
}
