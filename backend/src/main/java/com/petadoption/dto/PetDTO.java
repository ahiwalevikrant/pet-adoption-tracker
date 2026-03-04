package com.petadoption.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {
    
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Species is required")
    @Size(max = 50, message = "Species must not exceed 50 characters")
    private String species;

    @Size(max = 100, message = "Breed must not exceed 100 characters")
    private String breed;

    private Integer age;

    @Size(max = 20, message = "Gender must not exceed 20 characters")
    private String gender;

    private String description;

    private String imageUrl;

    private Boolean isAdopted;

    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String location;

    @Size(max = 100, message = "Owner name must not exceed 100 characters")
    private String ownerName;

    @Size(max = 50, message = "Owner contact must not exceed 50 characters")
    private String ownerContact;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
