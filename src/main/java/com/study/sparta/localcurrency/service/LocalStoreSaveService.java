package com.study.sparta.localcurrency.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;
import com.study.sparta.localcurrency.repository.LocalStoreJdbcRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalStoreSaveService {

    private final LocalStoreJdbcRepository jdbcRepository;

    @Transactional
    public void saveStores(List<LocalStoreDTO> stores) {
        jdbcRepository.batchUpsertLocalStores(stores);
    }
}
