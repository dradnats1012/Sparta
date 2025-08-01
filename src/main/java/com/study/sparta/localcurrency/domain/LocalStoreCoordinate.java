package com.study.sparta.localcurrency.domain;

import org.locationtech.jts.geom.Point;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "local_store_coordinate")
@Getter
@NoArgsConstructor
public class LocalStoreCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "정제된 가맹점 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cleaned_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cleaned"))
    private LocalStoreCleaned cleaned;

    @Schema(description = "위치정보 (POINT)")
    @Column(name = "location", columnDefinition = "POINT", nullable = false)
    private Point location;

    @Builder
    public LocalStoreCoordinate(LocalStoreCleaned cleaned, Point location) {
        this.cleaned = cleaned;
        this.location = location;
    }
}