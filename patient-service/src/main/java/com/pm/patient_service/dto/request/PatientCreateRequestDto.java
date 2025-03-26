package com.pm.patient_service.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreateRequestDto {


    @NotNull(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 character")
    private String name;
    //@NotBlank(message = "email is required")
    @NotNull(message = "email is required")
    @Email(message = "Provide a valid email")
    private String email;
    @NotNull(message = "address is required")
    private String address;
    @NotNull(message = "AdmissionDate is required")
    private String admissionDate;
    @NotNull(message = "Date od birth is required")
    private String dateOfBirth;




}
