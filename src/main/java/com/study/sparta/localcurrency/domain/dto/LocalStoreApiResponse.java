package com.study.sparta.localcurrency.domain.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class LocalStoreApiResponse {
    private Response response;

    @Getter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    public static class Header {
        private String resultCode;
        private String resultMsg;
        private String type;
    }

    @Getter
    public static class Body {
        private int totalCount;
        private List<LocalStoreDTO> items;
    }
}