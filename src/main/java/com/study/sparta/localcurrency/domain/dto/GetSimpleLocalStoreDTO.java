package com.study.sparta.localcurrency.domain.dto;

import java.util.UUID;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

public record GetSimpleLocalStoreDTO(
    UUID uuid,
    Double latitude,
    Double longitude
) {
    public static GetSimpleLocalStoreDTO from(LocalStoreCleaned store) {
        return new GetSimpleLocalStoreDTO(
            store.getUuid(),
            store.getLatitude(),
            store.getLongitude()
        );
    }
}
