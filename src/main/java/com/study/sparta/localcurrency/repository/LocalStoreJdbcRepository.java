package com.study.sparta.localcurrency.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LocalStoreJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void upsertAll(List<LocalStoreDTO> storeList) {
        String sql = """
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

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                LocalStoreDTO store = storeList.get(i);
                ps.setString(1, store.affiliateNm());
                ps.setString(2, store.localBill());
                ps.setString(3, store.ctpvNm());
                ps.setString(4, store.sggNm());
                ps.setString(5, store.lctnRoadNmAddr());
                ps.setString(6, store.lctnLotnoAddr());
                ps.setString(7, store.sectorNm());
                ps.setString(8, store.mainPrd());
                ps.setString(9, store.telno());
                ps.setString(10, store.insttCode());
                ps.setString(11, store.insttNm());
                ps.setDate(12, store.crtrYmd() != null ? Date.valueOf(store.crtrYmd()) : null);
            }

            @Override
            public int getBatchSize() {
                return storeList.size();
            }
        });
    }
}
