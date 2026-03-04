package com.petadoption.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedingScheduleDTO {
    
    private Long id;

    @NotNull(message = "Pet ID is required")
    private Long petId;

    @Size(max = 100, message = "Food type must not exceed 100 characters")
    private String foodType;

    @Size(max = 50, message = "Portion size must not exceed 50 characters")
    private String portionSize;

    private LocalTime feedingTime;

    @Size(max = 50, message = "Frequency must not exceed 50 characters")
    private String frequency;

    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Pet name for display purposes
    private String petName;
}
