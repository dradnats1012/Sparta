package com.study.sparta.localcurrency.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocalStoreApiPageResult {
    private final List<LocalStoreDTO> items;
    private final int totalCount;
}
