package com.pm.patient_service.service;


import com.pm.patient_service.dto.request.PatientCreateRequestDto;
import com.pm.patient_service.dto.response.PatientResponseDto;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.PatientEntity;
import com.pm.patient_service.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(true == patientRepository.existsByEmail(patientCreateRequestDto.getEmail()))
            throw new EmailAlreadyExistsException();
        PatientEntity patient = patientRepository
            .save(PatientMapper.patientCreateRequestDtoToEntity(patientCreateRequestDto));

        return PatientMapper.patientEntityToDto(patient);
    }

}
