package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.LocalStore;

public record GetLocalStoreMainDTO(
    String storeName,
    String localBill,
    String cityName,
    String sggName,
    String roadAddress
) {

    public static GetLocalStoreMainDTO from(LocalStore store) {
        return new GetLocalStoreMainDTO(
            store.getStoreName(),
            store.getLocalBill(),
            store.getCityName(),
            store.getSggName(),
            store.getRoadAddress()
        );
    }
}
