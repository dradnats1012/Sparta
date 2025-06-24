package com.study.sparta.localcurrency.repository;

import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LocalStoreBatchSetter implements BatchPreparedStatementSetter {

    private final List<LocalStoreDTO> storeList;

    public LocalStoreBatchSetter(List<LocalStoreDTO> storeList) {
        this.storeList = storeList;
    }

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
}