package com.pm.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.pm.exception.PatientManagementErrorCode.ErrorCategory.TECHNICAL;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@Getter
public enum PatientManagementErrorCode {

    EMAIL_ALREADY_EXIST(ErrorCategory.BUSINESS, "701", "patient with same email already exists", HttpStatus.BAD_REQUEST),
    JSON_PARSE_FAILURE(TECHNICAL, "702", "failed to parse json", INTERNAL_SERVER_ERROR)
            ;


    private final ErrorCategory category;
    private final String code;
    private final String DisplayMessage;
    private final HttpStatus httpStatus;

    public enum ErrorCategory{
        TECHNICAL,
        BUSINESS
    }
}
