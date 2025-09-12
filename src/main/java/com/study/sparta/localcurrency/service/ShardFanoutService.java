/*
package com.study.sparta.localcurrency.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;

@Service
public class ShardFanoutService {

    private final JdbcTemplate s1;
    private final JdbcTemplate s2;
    private final JdbcTemplate s3;
    private final JdbcTemplate s4;

    public ShardFanoutService(
        @Qualifier("s1Jdbc") JdbcTemplate s1,
        @Qualifier("s2Jdbc") JdbcTemplate s2,
        @Qualifier("s3Jdbc") JdbcTemplate s3,
        @Qualifier("s4Jdbc") JdbcTemplate s4
    ) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    public long totalCleanedCount() {
        String sql = "SELECT COUNT(*) FROM local_store_cleaned";
        return Objects.requireNonNull(s1.queryForObject(sql, Long.class))
            + Objects.requireNonNull(s2.queryForObject(sql, Long.class))
            + Objects.requireNonNull(s3.queryForObject(sql, Long.class))
            + Objects.requireNonNull(s4.queryForObject(sql, Long.class));
    }

    public Slice<GetLocalStoreMainDTO> searchStores(String storeName, Pageable pageable) {
        String keyword = Optional.ofNullable(storeName).orElse("").trim();
        if (keyword.isEmpty())
            return new SliceImpl<>(List.of(), pageable, false);

        int offset = Math.toIntExact(pageable.getOffset());
        int size = pageable.getPageSize();
        int fetchPerShard = offset + size + 1;

        List<LocalStoreCleaned> merged = new ArrayList<>(size * 4 + 4);
        merged.addAll(queryShardEntity(s1, keyword, fetchPerShard));
        merged.addAll(queryShardEntity(s2, keyword, fetchPerShard));
        merged.addAll(queryShardEntity(s3, keyword, fetchPerShard));
        merged.addAll(queryShardEntity(s4, keyword, fetchPerShard));

        merged.sort(Comparator.comparing(LocalStoreCleaned::getId).reversed());

        int end = Math.min(merged.size(), offset + size);
        List<GetLocalStoreMainDTO> pageContent =
            (offset >= merged.size()) ? List.of()
                : merged.subList(offset, end).stream()
                .map(GetLocalStoreMainDTO::from)
                .toList();

        boolean hasNext = merged.size() > end;

        return new SliceImpl<>(pageContent, pageable, hasNext);
    }

    private List<LocalStoreCleaned> queryShardEntity(JdbcTemplate jt, String keyword, int limit) {
        String sql = """
                SELECT
                    id,
                    BIN_TO_UUID(uuid) AS uuid, 
                    store_name   AS storeName,
                    region       AS region,
                    address      AS address,
                    tel_number   AS telNumber,
                    latitude     AS latitude,
                    longitude    AS longitude,
                    local_bill AS localBill, 
                    sector_name AS sectorName
                FROM local_store_cleaned
                WHERE MATCH(store_name) AGAINST (? IN NATURAL LANGUAGE MODE)
                LIMIT ?
            """;

        return jt.query(sql, ps -> { ps.setString(1, keyword); ps.setInt(2, limit); }, (rs, i) -> {
            LocalStoreCleaned e = new LocalStoreCleaned();
            e.setId(rs.getLong("id"));
            String uuidStr = rs.getString("uuid");
            e.setUuid(uuidStr != null ? UUID.fromString(uuidStr) : null);
            e.setStoreName(rs.getString("storeName"));
            e.setRegion(rs.getString("region"));
            e.setAddress(rs.getString("address"));
            e.setTelNumber(rs.getString("telNumber"));
            BigDecimal lat = rs.getBigDecimal("latitude");
            e.setLatitude(lat != null ? lat.doubleValue() : null);
            BigDecimal lon = rs.getBigDecimal("longitude");
            e.setLongitude(lon != null ? lon.doubleValue() : null);

            e.setLocalBill(rs.getString("localBill"));
            e.setSectorName(rs.getString("sectorName"));

            return e;
        });
    }
}
*/
