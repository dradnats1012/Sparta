package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.LocalStoreCoordinate;

public record GetLocalStoreMainDTO(
    Long id,
    String storeName,
    String localBill,
    String region,
    String roadAddress,
    Double latitude,
    Double longitude
) {

    public static GetLocalStoreMainDTO fromLocalStoreCleaned(LocalStoreCleaned store) {
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

    public static GetLocalStoreMainDTO fromLocalStoreCoordinate(LocalStoreCoordinate store) {
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
