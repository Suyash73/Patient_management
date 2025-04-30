package com.pm.service;


import com.pm.exception.PatientManagementGatewayException;
import com.pm.dto.request.PatientCreateRequestDto;
import com.pm.dto.response.PatientResponseDto;
import com.pm.mapper.PatientMapper;
import com.pm.model.PatientEntity;
import com.pm.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pm.exception.PatientManagementErrorCode.EMAIL_ALREADY_EXIST;


@Slf4j
@AllArgsConstructor
@Service
public class PatientService {

    private PatientRepository patientRepository;

    public List<PatientResponseDto> getAllPatientDetails (){
        List<PatientEntity> allPatient = patientRepository.findAll();
        log.info(allPatient.toString());
        List<PatientResponseDto> responseDto = allPatient.stream()
                .map(PatientMapper::patientEntityToDto)
                .toList();
        return responseDto;
    }

    public PatientResponseDto createPatient(PatientCreateRequestDto patientCreateRequestDto){
        if(patientRepository.existsByEmail(patientCreateRequestDto.getEmail()))
            throw new PatientManagementGatewayException(EMAIL_ALREADY_EXIST);

        PatientEntity patient = patientRepository
            .save(PatientMapper.patientCreateRequestDtoToEntity(patientCreateRequestDto));

        return PatientMapper.patientEntityToDto(patient);
    }

}
