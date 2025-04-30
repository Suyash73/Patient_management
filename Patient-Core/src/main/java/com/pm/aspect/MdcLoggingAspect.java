package com.pm.aspect;


import com.pm.annotation.LogWithMdc;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MdcLoggingAspect {

    @Pointcut("@annotation(com.pm.annotation.LogWithMdc)")
    public void logWithMdcMethods() {
    }

    @Before("logWithMdcMethods() && @annotation(logWithMdc)")
    public void beforeMethodExecution(LogWithMdc logWithMdc) {
        MDC.put("event", logWithMdc.value());
    }

    @After("logWithMdcMethods()")
    public void afterMethodExecution() {
        MDC.remove("event");
    }
}
