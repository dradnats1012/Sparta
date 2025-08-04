package com.study.sparta.localcurrency.service;

import static com.study.sparta.localcurrency.domain.dto.GetInstitutionCodesDTO.GetInstitutionCodeDTO;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.sparta.localcurrency.domain.InstitutionCode;
import com.study.sparta.localcurrency.domain.dto.GetInstitutionCodesDTO;
import com.study.sparta.localcurrency.domain.dto.GetInstitutionNamesDTO;
import com.study.sparta.localcurrency.repository.InstitutionCodeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InstitutionCodeService {

    private final InstitutionCodeRepository repository;

    public GetInstitutionCodesDTO getInstitutionCodes() {
        List<GetInstitutionCodeDTO> dtos = repository.findAll().stream()
            .map(e -> new GetInstitutionCodesDTO.GetInstitutionCodeDTO(
                e.getRegionName(),
                e.getCode()
            ))
            .toList();

        return new GetInstitutionCodesDTO(dtos);
    }

    public GetInstitutionNamesDTO getInstitutionNames() {
        List<String> regionNames = repository.findAll().stream()
            .map(InstitutionCode::getRegionName)
            .sorted()
            .toList();

        return new GetInstitutionNamesDTO(regionNames);
    }
}
