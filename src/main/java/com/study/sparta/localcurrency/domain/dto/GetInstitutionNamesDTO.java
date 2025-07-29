package com.study.sparta.localcurrency.domain.dto;

import java.util.List;

public record GetInstitutionNamesDTO(
    List<String> regionNames
) {
}
