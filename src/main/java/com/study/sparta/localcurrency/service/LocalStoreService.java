package com.study.sparta.localcurrency.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.LocalStoreCoordinate;
import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.repository.LocalStoreCleanedRepository;
import com.study.sparta.localcurrency.repository.LocalStoreCoordinateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalStoreService {

    private final LocalStoreCleanedRepository localStoreCleanedRepository;
    private final LocalStoreCoordinateRepository localStoreCoordinateRepository;

    public Slice<GetLocalStoreMainDTO> getStoresByStoreName(String storeName, Pageable pageable) {
        Slice<LocalStoreCleaned> stores = localStoreCleanedRepository.findAllByStoreName(storeName, pageable);
        return stores.map(GetLocalStoreMainDTO::fromLocalStoreCleaned);
    }

    public Slice<GetLocalStoreMainDTO> getStoresByRegion(String region, Pageable pageable) {
        Slice<LocalStoreCleaned> stores = localStoreCleanedRepository.findAllByRegion(region, pageable);
        return stores.map(GetLocalStoreMainDTO::fromLocalStoreCleaned);
    }

    public List<GetLocalStoreMainDTO> getStoresByDistance(Double latitude, Double longitude, int distance) {
        List<LocalStoreCoordinate> stores = localStoreCoordinateRepository.findByDistance(latitude, longitude, distance);
        return stores.stream().map(GetLocalStoreMainDTO::fromLocalStoreCoordinate).toList();
    }
}

