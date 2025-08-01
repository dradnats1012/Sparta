package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

public record GetSimpleLocalStoreDTO(
    Long id,
    String storeName,
    Double latitude,
    Double longitude
) {
    public static GetSimpleLocalStoreDTO from(LocalStoreCleaned store) {
        return new GetSimpleLocalStoreDTO(
            store.getId(),
            store.getStoreName(),
            store.getLatitude(),
            store.getLongitude()
        );
    }
}
