package com.study.sparta.localcurrency.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LocalStoreJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String UPSERT_SQL = """
            INSERT INTO local_store (
                affiliate_name, local_bill, ctpv_name, sgg_name, road_addr,
                lotno_addr, sector_name, main_prd, telno, instt_code, instt_name, crtr_ymd
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                local_bill = VALUES(local_bill),
                ctpv_name = VALUES(ctpv_name),
                sgg_name = VALUES(sgg_name),
                lotno_addr = VALUES(lotno_addr),
                sector_name = VALUES(sector_name),
                main_prd = VALUES(main_prd),
                telno = VALUES(telno),
                instt_code = VALUES(instt_code),
                instt_name = VALUES(instt_name),
                crtr_ymd = VALUES(crtr_ymd)
        """;

    public void batchUpsertLocalStores(List<LocalStoreDTO> storeList) {
        jdbcTemplate.batchUpdate(
            UPSERT_SQL,
            new LocalStoreBatchSetter(storeList)
        );
    }
}