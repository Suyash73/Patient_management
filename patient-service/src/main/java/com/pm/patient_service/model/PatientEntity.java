package com.pm.patient_service.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "patient_details")
public class PatientEntity {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String address;

    @NotNull
    @Field("date_of_birth")  // Ensure mapping to MongoDB field name
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull
    @Field("admission_date")  // Ensure mapping to MongoDB field name
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;
}
