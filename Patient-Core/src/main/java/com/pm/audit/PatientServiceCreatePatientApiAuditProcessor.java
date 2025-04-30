package com.pm.audit;

import com.pm.exception.PatientManagementGatewayException;
import com.pm.response.ResponseObject;
import com.pm.utils.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Optional;

@Slf4j
public class PatientServiceCreatePatientApiAuditProcessor implements SmartLogger {

    @Override
    public void process(Optional<Object> response, Object[] objects, Optional<Throwable> exceptionOptional) {
        try {
            //String createRequest = ObjectMapperUtil.convertObjectToStringSilently(objects[0]);
            MDC.put("request", "createRequest");
            if (response.isPresent()) {
                if (response.get() instanceof ResponseObject<?>) {
                    MDC.put("statusCode", String.valueOf(((ResponseObject<?>) response.get()).meta().code()));
                    MDC.put("response",
                            ObjectMapperUtil.convertObjectToStringSilently(((ResponseObject<?>) response.get()).data())
                            );
                }
                log.info("Audit Logged");
            } else
                exceptionOptional.ifPresent(throwable -> {
                    if (throwable instanceof PatientManagementGatewayException) {
                        try {
                            MDC.put("response",
                                    ObjectMapperUtil.convertObjectToStringSilently(((PatientManagementGatewayException) throwable).getPatientManagementErrorCode()));
                        } catch (Exception e) {
                            log.error("Failed to parse the json {}", e.getMessage());
                        }
                    } else {
                        MDC.put("response", throwable.getMessage());
                    }
                    log.error("Exception occurred");
                });
            MDC.remove("request");
            MDC.remove("response");
        } catch (Exception exception) {
            log.error("Failed to Audit log of Api ", exception);
        }
    }
}
