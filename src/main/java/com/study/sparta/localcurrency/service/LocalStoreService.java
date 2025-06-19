package com.study.sparta.localcurrency.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.repository.LocalStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalStoreService {

    // TODO : 정제된 테이블 바라보도록 수정하기
    private final LocalStoreRepository localStoreRepository;

    public Page<GetLocalStoreMainDTO> getStores(String cityName, String sggName, Pageable pageable) {
        return localStoreRepository.findByPage(cityName, sggName, pageable);
    }

    public List<GetLocalStoreMainDTO> getLocalStoresByOffset(String cityName, String sggName, int page, int size) {
        return localStoreRepository.findByOffset(cityName, sggName, size, page);
    }
}
