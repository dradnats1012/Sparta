package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

public record GetLocalStoreMainDTO(
    Long id,
    String storeName,
    String localBill,
    String region,
    String roadAddress,
    Double latitude,
    Double longitude
) {

    public static GetLocalStoreMainDTO from(LocalStoreCleaned store) {
        return new GetLocalStoreMainDTO(
            store.getId(),
            store.getStoreName(),
            store.getLocalBill(),
            store.getRegion(),
            store.getAddress(),
            store.getLatitude(),
            store.getLongitude()
        );
    }
}
