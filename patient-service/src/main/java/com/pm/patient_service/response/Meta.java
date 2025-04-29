package com.pm.patient_service.response;

public record Meta(String code, String message, String displayMessage) {
    public Meta(String code, String message, String displayMessage){
        this.code = code;
        this.message = message;
        this.displayMessage = displayMessage;
//        this(code, message, displayMessage);
    }
}
