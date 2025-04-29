package com.pm.patient_service.controller;


import com.pm.patient_service.dto.request.PatientCreateRequestDto;
import com.pm.patient_service.dto.response.PatientResponseDto;
import com.pm.patient_service.response.ResponseObject;
import com.pm.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SimpleTimeZone;

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
    public ResponseEntity<PatientResponseDto> createPatient(@Valid@RequestBody PatientCreateRequestDto createRequestDto){
        PatientResponseDto responseDto = patientService.createPatient(createRequestDto);
        return ResponseEntity.ok().body(responseDto);

    }
}
