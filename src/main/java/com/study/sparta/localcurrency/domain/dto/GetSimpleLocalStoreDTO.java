package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

public record GetSimpleLocalStoreDTO(
    Long id,
    Double latitude,
    Double longitude
) {
    public static GetSimpleLocalStoreDTO from(LocalStoreCleaned store) {
        return new GetSimpleLocalStoreDTO(
            store.getId(),
            store.getLatitude(),
            store.getLongitude()
        );
    }
}
