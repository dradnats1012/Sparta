package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.LocalStore;

public record GetLocalStoreMainDTO(
    String affiliateName,
    String localBill,
    String ctpvName,
    String sggName,
    String roadAddr
) {

    public static GetLocalStoreMainDTO from(LocalStore store) {
        return new GetLocalStoreMainDTO(
            store.getAffiliateName(),
            store.getLocalBill(),
            store.getCtpvName(),
            store.getSggName(),
            store.getRoadAddr()
        );
    }
}
