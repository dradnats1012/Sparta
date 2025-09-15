package com.study.sparta.localcurrency.domain.dto;

import com.study.sparta.localcurrency.domain.InstitutionCode;

public record GetInstitutionNamePosition(
    String regionName,
    Double latitude,
    Double longitude
) {

    public static GetInstitutionNamePosition from(InstitutionCode institutionCode) {
        return new GetInstitutionNamePosition(
            institutionCode.getRegionName(),
            institutionCode.getLatitude(),
            institutionCode.getLongitude()
        );
    }
}
