package com.pm.aspect;


import com.pm.audit.SmartLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import com.pm.annotation.Loggable;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Aspect
@Component
public class LoggingAspects {


    @Around("@annotation(loggable)")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        Object response = null;
        Optional<Throwable> exception = Optional.empty();
        long startTime = System.currentTimeMillis();
        try {
            response = joinPoint.proceed();
        } catch (Throwable e) {
            exception = Optional.of(e);
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            processAudit(loggable, response, joinPoint.getArgs(), exception, loggable.apiName(), startTime, endTime);
        }
        return response;
    }

    private void processAudit(Loggable loggable, Object response, Object[] args, Optional<Throwable> exception,
                              String apiName, long startTime, long endTime) {
        try {
            Class<? extends SmartLogger> auditClass = loggable.auditProcessor();
            SmartLogger auditProcessor = auditClass.getDeclaredConstructor().newInstance();
            MDC.put("logger_name", "Audit_logger");
            MDC.put("auditName", apiName);
            MDC.put("timeTaken", String.valueOf(endTime - startTime));
            auditProcessor.process(Optional.ofNullable(response), args, exception);
            MDC.remove("logger_name");
            MDC.remove("auditName");
            MDC.remove("timeTaken");
        } catch (Exception e) {
            log.error("Error invoking audit processor: {}", e.getMessage());
        }
    }
}