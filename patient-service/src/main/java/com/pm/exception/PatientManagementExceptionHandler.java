package com.pm.exception;


import com.pm.exception.PatientManagementErrorCode;
import com.pm.exception.PatientManagementGatewayException;
import com.pm.response.Meta;
import com.pm.response.ResponseObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class PatientManagementExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){

        log.info("Validation Exception occurred.");
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientManagementGatewayException.class)
    public  ResponseEntity<ResponseObject<Object>> handleGatewayException (PatientManagementGatewayException ex){
        log.info("Patient Gateway Exception occurred.");
        PatientManagementErrorCode errorCode = ex.getPatientManagementErrorCode();
        String message = errorCode.name();
        String displayMessage = errorCode.getDisplayMessage();
        if(StringUtils.hasText(ex.getMessage())){
            displayMessage = ex.getMessage();
        }else{
            displayMessage = errorCode.getDisplayMessage();
        }
        Meta meta = new Meta(errorCode.getCode(), errorCode.name(), errorCode.getDisplayMessage());
        ResponseObject<Object> objectResponseObject = new ResponseObject<>(meta, null);
        return new ResponseEntity<>(objectResponseObject, errorCode.getHttpStatus());
    }



}
