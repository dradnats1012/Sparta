package com.study.sparta.localcurrency.domain.dto;

import java.util.UUID;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

public record GetLocalStoreMainDTO(
    UUID uuid,
    String storeName,
    String localBill,
    String region,
    String telNumber,
    String sectorName,
    String address,
    Double latitude,
    Double longitude
) {

    public static GetLocalStoreMainDTO from(LocalStoreCleaned store) {
        return new GetLocalStoreMainDTO(
            store.getUuid(),
            store.getStoreName(),
            store.getLocalBill(),
            store.getRegion(),
            store.getTelNumber(),
            store.getSectorName(),
            store.getAddress(),
            store.getLatitude(),
            store.getLongitude()
        );
    }
}
