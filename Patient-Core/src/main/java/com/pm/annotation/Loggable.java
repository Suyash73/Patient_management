package com.pm.annotation;

import com.pm.audit.SmartLogger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Use to design a custom annotation @Loggable
    @Target(ElementType.METHOD) //Annotations can be applied to only methods
    @Retention(RetentionPolicy.RUNTIME) // Annotation availabe at runtime
    public @interface Loggable {

        Class<? extends SmartLogger> auditProcessor();
        String apiName();

    }
