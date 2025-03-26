package com.pm.patient_service.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.pm.patient_service.exception.PatientManagementErrorCode.ErrorCategory.BUSINESS;

@AllArgsConstructor
@Getter
public enum PatientManagementErrorCode {

    EMAIL_ALREADY_EXIST(BUSINESS, "701", "patient with same email already exists", HttpStatus.BAD_REQUEST);


    private final ErrorCategory category;
    private final String code;
    private final String DisplayMessage;
    private final HttpStatus httpStatus;

    public enum ErrorCategory{
        TECHNICAL,
        BUSINESS
    }
}
