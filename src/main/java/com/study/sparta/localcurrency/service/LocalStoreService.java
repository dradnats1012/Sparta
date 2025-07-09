package com.study.sparta.localcurrency.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.repository.LocalStoreCleanedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalStoreService {

    private final LocalStoreCleanedRepository localStoreRepository;

    public Page<GetLocalStoreMainDTO> getStoresByStoreName(String storeName, Pageable pageable) {
        Page<LocalStoreCleaned> stores = localStoreRepository.findAllByStoreName(storeName, pageable);
        return stores.map(GetLocalStoreMainDTO::from);
    }

    public Page<GetLocalStoreMainDTO> getStoresByRegion(String region, Pageable pageable) {
        Page<LocalStoreCleaned> stores = localStoreRepository.findAllByRegion(region, pageable);
        return stores.map(GetLocalStoreMainDTO::from);
    }

    public List<GetLocalStoreMainDTO> getStoresByDistance(Double latitude, Double longitude) {
        List<LocalStoreCleaned> stores = localStoreRepository.findByDistance(latitude, longitude, 3000);
        return stores.stream().map(GetLocalStoreMainDTO::from).toList();
    }
}

