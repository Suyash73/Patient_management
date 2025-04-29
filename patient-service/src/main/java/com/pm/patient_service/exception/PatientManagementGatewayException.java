package com.pm.patient_service.exception;


import lombok.Getter;

@Getter
public class PatientManagementGatewayException extends RuntimeException {

    private final PatientManagementErrorCode patientManagementErrorCode;

    public PatientManagementGatewayException(PatientManagementErrorCode patientManagementErrorCode){
        super(patientManagementErrorCode.getDisplayMessage());
        this.patientManagementErrorCode = patientManagementErrorCode;
    }
    public PatientManagementGatewayException(PatientManagementErrorCode patientManagementErrorCode, Object... args){
        super(String.format(patientManagementErrorCode.getDisplayMessage(), args));
        this.patientManagementErrorCode = patientManagementErrorCode;
    }
    public PatientManagementGatewayException(PatientManagementErrorCode patientManagementErrorCode, Throwable cause, Object... args){
        super(String.format(patientManagementErrorCode.getDisplayMessage(), args), cause);
        this.patientManagementErrorCode = patientManagementErrorCode;
    }
    public PatientManagementGatewayException(PatientManagementErrorCode patientManagementErrorCode, String displayMessage){
        super(displayMessage);
        this.patientManagementErrorCode = patientManagementErrorCode;
    }
    public PatientManagementGatewayException(PatientManagementErrorCode patientManagementErrorCode, Throwable cause){
        super(cause);
        this.patientManagementErrorCode = patientManagementErrorCode;
    }

}
