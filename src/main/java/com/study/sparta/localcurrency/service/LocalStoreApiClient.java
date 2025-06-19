package com.study.sparta.localcurrency.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.study.sparta.localcurrency.domain.dto.LocalStoreApiPageResult;
import com.study.sparta.localcurrency.domain.dto.LocalStoreApiResponse;
import com.study.sparta.localcurrency.domain.dto.LocalStoreDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalStoreApiClient {

    private final RestTemplate restTemplate;

    @Value("${OPEN_API_KEY_PUBLIC}")
    private String SERVICE_KEY;

    public LocalStoreApiPageResult fetchLocalStores(String insttCode, int page) {
        URI uri = UriComponentsBuilder
            .fromHttpUrl("http://api.data.go.kr/openapi/tn_pubr_public_local_bill_api")
            .queryParam("serviceKey", SERVICE_KEY)
            .queryParam("pageNo", page)
            .queryParam("numOfRows", 1500)
            .queryParam("type", "json")
            .queryParam("instt_code", insttCode)
            .build(true)
            .toUri();

        LocalStoreApiResponse response = restTemplate.getForObject(uri, LocalStoreApiResponse.class);

        if (response == null || response.getResponse() == null || response.getResponse().getBody() == null) {
            return new LocalStoreApiPageResult(Collections.emptyList(), 0);
        }

        List<LocalStoreDTO> items = response.getResponse().getBody().getItems();
        int totalCount = response.getResponse().getBody().getTotalCount();

        return new LocalStoreApiPageResult(items, totalCount);
    }
}
