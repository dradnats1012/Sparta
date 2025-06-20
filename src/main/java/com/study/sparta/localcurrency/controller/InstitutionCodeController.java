package com.study.sparta.localcurrency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.localcurrency.domain.dto.GetInstitutionCodesDTO;
import com.study.sparta.localcurrency.service.InstitutionCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/institution")
public class InstitutionCodeController {

    private final InstitutionCodeService service;

    @GetMapping
    public ResponseEntity<GetInstitutionCodesDTO> getInstitutionCodes(){
        return ResponseEntity.ok(service.getInstitutionCodes());
    }
}
