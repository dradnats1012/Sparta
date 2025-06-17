package com.study.sparta.localcurrency.domain.dto;

import java.util.List;

public record GetInstitutionCodesDTO(
    List<GetInstitutionCodeDTO> institutionCodes
) {
    public record GetInstitutionCodeDTO(
        String regionName,
        String institutionCode
    ) {
    }
}