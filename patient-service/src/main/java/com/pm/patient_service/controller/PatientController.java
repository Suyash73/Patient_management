package com.pm.patient_service.controller;


import com.pm.patient_service.dto.request.PatientCreateRequestDto;
import com.pm.patient_service.dto.response.PatientResponseDto;
import com.pm.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/allpatients")
    public ResponseEntity<List<PatientResponseDto>> getPatients(){
        List<PatientResponseDto> patients = patientService.getAllPatientDetails();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping("/createpatient")
    public ResponseEntity<PatientResponseDto> createPatient(@Valid@RequestBody PatientCreateRequestDto createRequestDto){
        PatientResponseDto responseDto = patientService.createPatient(createRequestDto);
        return ResponseEntity.ok().body(responseDto);

    }
}
