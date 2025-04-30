package com.pm.controller;


import com.pm.annotation.Loggable;
import com.pm.audit.PatientServiceCreatePatientApiAuditProcessor;
import com.pm.dto.request.PatientCreateRequestDto;
import com.pm.dto.response.PatientResponseDto;
import com.pm.service.PatientService;
import com.pm.response.ResponseObject;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private static final String PATIENT_DETAILS_FETCHED_SUCCESSFULLY = "All patients details fetched successfully";

    private final PatientService patientService;

    @GetMapping("/allpatients")
    public ResponseObject<List<PatientResponseDto>> getPatients(){
        var patients = patientService.getAllPatientDetails();
        return new ResponseObject<>(PATIENT_DETAILS_FETCHED_SUCCESSFULLY,patients);
    }

    @PostMapping("/createpatient")
    @Loggable(auditProcessor = PatientServiceCreatePatientApiAuditProcessor.class,apiName ="CREATE_PATIENT")
    public ResponseEntity<PatientResponseDto> createPatient(@Valid@RequestBody PatientCreateRequestDto createRequestDto){
        PatientResponseDto responseDto = patientService.createPatient(createRequestDto);
        return ResponseEntity.ok().body(responseDto);

    }
}
