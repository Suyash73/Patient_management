package com.pm.audit;
import java.util.Optional;

public interface SmartLogger {

    void process(Optional<Object> response, Object[] objects, Optional<Throwable> exceptionOptional);

}
