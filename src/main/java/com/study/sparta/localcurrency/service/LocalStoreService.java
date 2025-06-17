package com.study.sparta.localcurrency.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.sparta.localcurrency.domain.dto.GetLocalStoreMainDTO;
import com.study.sparta.localcurrency.repository.LocalStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalStoreService {

    private final LocalStoreRepository localStoreRepository;

    public Page<GetLocalStoreMainDTO> getStores(String ctpv, String sgg, Pageable pageable) {
        return localStoreRepository.findByPage(ctpv, sgg, pageable);
    }

    // TODO : 변수명 바꾸기
    public List<GetLocalStoreMainDTO> getLocalStoresByOffset(String ctpv, String sgg, int page, int size) {
        return localStoreRepository.findByOffset(ctpv, sgg, size, page);
    }
}
