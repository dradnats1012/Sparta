package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;

public record GetLocalStoreMainDTO(
    String storeName,
    String localBill,
    String region,
    String roadAddress
) {

    public static GetLocalStoreMainDTO from(LocalStoreCleaned store) {
        return new GetLocalStoreMainDTO(
            store.getStoreName(),
            store.getLocalBill(),
            store.getRegion(),
            store.getAddress()
        );
    }
}
