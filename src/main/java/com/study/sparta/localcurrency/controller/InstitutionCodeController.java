package com.study.sparta.localcurrency.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.localcurrency.domain.dto.GetInstitutionCodesDTO;
import com.study.sparta.localcurrency.domain.dto.GetInstitutionNamePosition;
import com.study.sparta.localcurrency.domain.dto.GetInstitutionNamesDTO;
import com.study.sparta.localcurrency.service.InstitutionCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/institutions")
public class InstitutionCodeController {

    private final InstitutionCodeService service;

    @GetMapping
    public ResponseEntity<GetInstitutionCodesDTO> getInstitutionCodes() {
        return ResponseEntity.ok(service.getInstitutionCodes());
    }

    @GetMapping("/names")
    public ResponseEntity<GetInstitutionNamesDTO> getInstitutionNames() {
        return ResponseEntity.ok(service.getInstitutionNames());
    }

    @GetMapping("/v2/names")
    public ResponseEntity<List<GetInstitutionNamePosition>> getInstitutionNamesAndPosition() {
        return ResponseEntity.ok(service.getInstitutionNamesAndPosition());
    }
}
