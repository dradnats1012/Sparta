package com.study.sparta.localcurrency.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.sparta.localcurrency.domain.LocalStoreCleaned;
import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.domain.dto.GetSimpleLocalStoreDTO;
import com.study.sparta.localcurrency.repository.LocalStoreCleanedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalStoreService {

    private final LocalStoreCleanedRepository localStoreCleanedRepository;

    public Slice<GetLocalStoreMainDTO> getStoresByStoreName(String storeName, Pageable pageable) {
        Slice<LocalStoreCleaned> stores = localStoreCleanedRepository.findAllByStoreName(storeName, pageable);
        return stores.map(GetLocalStoreMainDTO::fromLocalStoreCleaned);
    }

    public Slice<GetLocalStoreMainDTO> getStoresByRegion(String region, Pageable pageable) {
        Slice<LocalStoreCleaned> stores = localStoreCleanedRepository.findAllByRegion(region, pageable);
        return stores.map(GetLocalStoreMainDTO::fromLocalStoreCleaned);
    }

    public List<GetLocalStoreMainDTO> getStoresByDistance(Double latitude, Double longitude, int distance) {
        List<LocalStoreCleaned> stores = localStoreCleanedRepository.findStoresWithinDistanceByBuffer(
            latitude, longitude, distance
        );
        return stores.stream().map(GetLocalStoreMainDTO::fromLocalStoreCleaned).toList();
    }

    public List<GetLocalStoreMainDTO> getStoresByLineString(Double leftLatitude, Double leftLongitude,
        Double rightLatitude, Double rightLongitude) {
        List<LocalStoreCleaned> stores = localStoreCleanedRepository.findStoresInBoundingBox(
            leftLatitude, leftLongitude, rightLatitude, rightLongitude
        );
        return stores.stream().map(GetLocalStoreMainDTO::fromLocalStoreCleaned).toList();
    }

    public List<GetSimpleLocalStoreDTO> getSimpleStores(Double latitude, Double longitude, int distance) {
        List<LocalStoreCleaned> stores = localStoreCleanedRepository.findStoresWithinDistanceByBuffer(
            latitude, longitude, distance
        );
        return stores.stream().map(GetSimpleLocalStoreDTO::from).toList();
    }

    public GetLocalStoreMainDTO getById(Long id) {
        LocalStoreCleaned localStoreCleaned = localStoreCleanedRepository.getById(id);
        return GetLocalStoreMainDTO.fromLocalStoreCleaned(localStoreCleaned);
    }
}

