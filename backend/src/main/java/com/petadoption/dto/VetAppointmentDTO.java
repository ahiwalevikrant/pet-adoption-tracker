package com.petadoption.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VetAppointmentDTO {
    
    private Long id;

    @NotNull(message = "Pet ID is required")
    private Long petId;

    private LocalDateTime appointmentDate;

    @Size(max = 100, message = "Veterinarian must not exceed 100 characters")
    private String veterinarian;

    @Size(max = 100, message = "Clinic must not exceed 100 characters")
    private String clinic;

    @Size(max = 255, message = "Reason must not exceed 255 characters")
    private String reason;

    @Size(max = 20, message = "Status must not exceed 20 characters")
    private String status;

    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Pet name for display purposes
    private String petName;
}
