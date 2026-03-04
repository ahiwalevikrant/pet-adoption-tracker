package com.petadoption.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationDTO {
    
    private Long id;

    @NotNull(message = "Pet ID is required")
    private Long petId;

    @NotBlank(message = "Vaccination name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    private LocalDate dateAdministered;

    private LocalDate nextDueDate;

    @Size(max = 100, message = "Veterinarian must not exceed 100 characters")
    private String veterinarian;

    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Pet name for display purposes
    private String petName;
}
