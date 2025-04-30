package com.pm.mapper;

import com.pm.dto.request.PatientCreateRequestDto;
import com.pm.dto.response.PatientResponseDto;
import com.pm.model.PatientEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class PatientMapper {
    public static PatientResponseDto patientEntityToDto(PatientEntity patientEntity){
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patientEntity.getId());
        patientResponseDto.setName(patientEntity.getName());
        patientResponseDto.setAddress(patientEntity.getAddress());
        patientResponseDto.setEmail(patientEntity.getEmail());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault()); // Ensure correct timezone

        patientResponseDto.setAdmissionDate(Optional.ofNullable(patientEntity.getAdmissionDate())
                .map(instant -> formatter.format(instant)).orElse(null));

        patientResponseDto.setDateOfBirth(Optional.ofNullable(patientEntity.getDateOfBirth())
                .map(instant -> formatter.format(instant)).orElse(null));

        return patientResponseDto;
    }
    public static PatientEntity patientCreateRequestDtoToEntity(PatientCreateRequestDto newPatient){
        PatientEntity patient = new PatientEntity();

        patient.setName(newPatient.getName());
        patient.setAddress(newPatient.getAddress());
        patient.setEmail(newPatient.getEmail());
        patient.setAdmissionDate(
                LocalDate.parse(newPatient.getAdmissionDate())
        );
        patient.setDateOfBirth(
                LocalDate.parse(newPatient.getDateOfBirth())
        );

        return patient;
    }

}
