package com.pm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pm.exception.PatientManagementErrorCode;
import com.pm.exception.PatientManagementGatewayException;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass

//This class,  is a utility class for JSON serialization and deserialization using the Jackson library
public class ObjectMapperUtil {

    @Getter
    private final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    }

    public static <T> T convertJsonToObject(String json, Class<T> targetType) {
        try {
            return getObjectMapper().readValue(json, targetType);
        } catch (Exception e) {
            throw new PatientManagementGatewayException(PatientManagementErrorCode.JSON_PARSE_FAILURE, e);
        }
    }

    public static <T> T convertJsonToObject(byte[] bytes, Class<T> targetType) {
        try {
            return getObjectMapper().readValue(bytes, targetType);
        } catch (Exception e) {
            throw new PatientManagementGatewayException(PatientManagementErrorCode.JSON_PARSE_FAILURE, e);
        }
    }

    public static <T> String convertObjectToString(T object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("Object mapping failed", e);
            throw new PatientManagementGatewayException(PatientManagementErrorCode.JSON_PARSE_FAILURE);
        }
    }

    public static <T> String convertObjectToStringSilently(T object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("Object mapping failed", e);
            return null;
        }
    }
}
