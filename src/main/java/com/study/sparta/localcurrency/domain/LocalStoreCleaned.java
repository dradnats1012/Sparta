package com.study.sparta.localcurrency.domain;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.locationtech.jts.geom.Point;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "local_store_cleaned")
@Getter
@Setter
@NoArgsConstructor
public class LocalStoreCleaned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Schema(description = "가맹점명")
    @Column(name = "store_name")
    private String storeName;

    @Schema(description = "사용가능지역화")
    @Column(name = "local_bill")
    private String localBill;

    @Schema(description = "지역")
    @Column(name = "region")
    private String region;

    @Schema(description = "주소")
    @Column(name = "address", length = 512)
    private String address;

    @Schema(description = "업종명")
    @Column(name = "sector_name")
    private String sectorName;

    @Schema(description = "주요상품")
    @Column(name = "main_product")
    private String mainProduct;

    @Schema(description = "전화번호")
    @Column(name = "tel_number")
    private String telNumber;

    @Schema(description = "제공기관코드")
    @Column(name = "institution_code")
    private String institutionCode;

    @Schema(description = "제공기관기관명")
    @Column(name = "institution_name")
    private String institutionName;

    @Schema(description = "데이터기준일자")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Schema(description = "위도")
    @Column(name = "latitude")
    private Double latitude;

    @Schema(description = "경도")
    @Column(name = "longitude")
    private Double longitude;

    @Schema(description = "위치정보")
    @Column(name = "location", columnDefinition = "POINT")
    private Point location;

    @JdbcTypeCode(Types.BINARY)
    @Column(name = "uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID uuid;

    @Schema(description = "조회수")
    @Column(name = "hits")
    private Long hits;

    @Builder
    public LocalStoreCleaned(
        String storeName,
        String localBill,
        String region,
        String address,
        String sectorName,
        String mainProduct,
        String telNumber,
        String institutionCode,
        String institutionName,
        LocalDate createdAt,
        Double latitude,
        Double longitude,
        Point location,
        UUID uuid,
        Long hits
    ) {
        this.storeName = storeName;
        this.localBill = localBill;
        this.region = region;
        this.address = address;
        this.sectorName = sectorName;
        this.mainProduct = mainProduct;
        this.telNumber = telNumber;
        this.institutionCode = institutionCode;
        this.institutionName = institutionName;
        this.createdAt = createdAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.uuid = uuid;
        this.hits = hits;
    }

    public void plusHits(){
        hits++;
    }
}
