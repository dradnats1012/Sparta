package com.study.sparta.localcurrency.domain;

import java.time.LocalDate;

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

@Entity
@Table(name = "local_store")
@Getter
@NoArgsConstructor
public class LocalStore {

    //TODO : 의미론적 변수명으로 변경
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "가맹점명")
    @Column(name = "affiliate_name")
    private String affiliateName;

    @Schema(description = "사용가능지역화")
    @Column(name = "local_bill")
    private String localBill;

    @Schema(description = "시도명")
    @Column(name = "ctpv_name")
    private String ctpvName;

    @Schema(description = "시군구명")
    @Column(name = "sgg_name")
    private String sggName;

    @Schema(description = "소재지도로명주소")
    @Column(name = "road_addr", length = 512)
    private String roadAddr;

    @Schema(description = "소재지지번주소")
    @Column(name = "lotno_addr")
    private String lotnoAddr;

    @Schema(description = "업종명")
    @Column(name = "sector_name")
    private String sectorName;

    @Schema(description = "주요상품")
    @Column(name = "main_prd")
    private String mainPrd;

    @Schema(description = "전화번호")
    @Column(name = "telno")
    private String telno;

    @Schema(description = "제공기관코드")
    @Column(name = "instt_code")
    private String insttCode;

    @Schema(description = "제공기관기관명")
    @Column(name = "instt_name")
    private String insttName;

    @Schema(description = "데이터기준일자")
    @Column(name = "crtr_ymd")
    private LocalDate crtrYmd;

    @Builder
    public LocalStore(
        String affiliateName,
        String localBill,
        String ctpvName,
        String sggName,
        String roadAddr,
        String lotnoAddr,
        String sectorName,
        String mainPrd,
        String telno,
        String insttCode,
        String insttName,
        LocalDate crtrYmd
    ) {
        this.affiliateName = affiliateName;
        this.localBill = localBill;
        this.ctpvName = ctpvName;
        this.sggName = sggName;
        this.roadAddr = roadAddr;
        this.lotnoAddr = lotnoAddr;
        this.sectorName = sectorName;
        this.mainPrd = mainPrd;
        this.telno = telno;
        this.insttCode = insttCode;
        this.insttName = insttName;
        this.crtrYmd = crtrYmd;
    }
}
