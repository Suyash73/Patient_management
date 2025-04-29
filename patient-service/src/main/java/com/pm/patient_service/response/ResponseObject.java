package com.pm.patient_service.response;

import com.pm.patient_service.exception.PatientManagementErrorCode;

public record ResponseObject<T> (Meta meta, T data){
    public ResponseObject(String displayMessage, T data){
        this(new Meta("200", "success", displayMessage), data);
    }

    public ResponseObject(PatientManagementErrorCode errorCode){
        this(new Meta(errorCode.getCode(), errorCode.name(), errorCode.getDisplayMessage()), null);
    }
}
