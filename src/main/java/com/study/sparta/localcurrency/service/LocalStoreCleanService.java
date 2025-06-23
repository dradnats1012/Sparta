package com.study.sparta.localcurrency.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalStoreCleanService {

    private final JdbcTemplate jdbcTemplate;

    public void upsertCleanedStores() {
        String sql = """
            INSERT INTO local_store_cleaned (
                store_name, local_bill, region, address,
                main_product, tel_number, institution_code,
                institution_name, create_at, sector_name
            )
            SELECT
                affiliate_name,
                local_bill,
                CONCAT(ctpv_name, ' ', sgg_name),
                CASE
                    WHEN road_addr IS NOT NULL AND road_addr != '' THEN road_addr
                    WHEN lotno_addr IS NOT NULL AND lotno_addr != '' THEN lotno_addr
                    ELSE NULL
                END,
                main_prd,
                telno,
                instt_code,
                instt_name,
                crtr_ymd,
                sector_name
            FROM local_store
            ON DUPLICATE KEY UPDATE
                local_bill = VALUES(local_bill),
                region = VALUES(region),
                address = VALUES(address),
                main_product = VALUES(main_product),
                tel_number = VALUES(tel_number),
                institution_code = VALUES(institution_code),
                institution_name = VALUES(institution_name),
                create_at = VALUES(create_at),
                sector_name = VALUES(sector_name)
        """;

        jdbcTemplate.update(sql);
    }
}
