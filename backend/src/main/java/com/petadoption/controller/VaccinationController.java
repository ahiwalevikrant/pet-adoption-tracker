package com.petadoption.controller;

import com.petadoption.dto.ApiResponse;
import com.petadoption.dto.VaccinationDTO;
import com.petadoption.service.VaccinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @GetMapping("/vaccinations")
    public ResponseEntity<ApiResponse<List<VaccinationDTO>>> getAllVaccinations() {
        List<VaccinationDTO> vaccinations = vaccinationService.getAllVaccinations();
        return ResponseEntity.ok(ApiResponse.success(vaccinations));
    }

    @GetMapping("/vaccinations/{id}")
    public ResponseEntity<ApiResponse<VaccinationDTO>> getVaccinationById(@PathVariable Long id) {
        VaccinationDTO vaccination = vaccinationService.getVaccinationById(id);
        return ResponseEntity.ok(ApiResponse.success(vaccination));
    }

    @GetMapping("/pets/{petId}/vaccinations")
    public ResponseEntity<ApiResponse<List<VaccinationDTO>>> getVaccinationsByPetId(@PathVariable Long petId) {
        List<VaccinationDTO> vaccinations = vaccinationService.getVaccinationsByPetId(petId);
        return ResponseEntity.ok(ApiResponse.success(vaccinations));
    }

    @GetMapping("/vaccinations/upcoming")
    public ResponseEntity<ApiResponse<List<VaccinationDTO>>> getUpcomingVaccinations() {
        List<VaccinationDTO> vaccinations = vaccinationService.getUpcomingVaccinations();
        return ResponseEntity.ok(ApiResponse.success(vaccinations));
    }

    @PostMapping("/pets/{petId}/vaccinations")
    public ResponseEntity<ApiResponse<VaccinationDTO>> createVaccination(
            @PathVariable Long petId,
            @Valid @RequestBody VaccinationDTO vaccinationDTO) {
        vaccinationDTO.setPetId(petId);
        VaccinationDTO createdVaccination = vaccinationService.createVaccination(vaccinationDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Vaccination created successfully", createdVaccination));
    }

    @PutMapping("/vaccinations/{id}")
    public ResponseEntity<ApiResponse<VaccinationDTO>> updateVaccination(
            @PathVariable Long id,
            @Valid @RequestBody VaccinationDTO vaccinationDTO) {
        VaccinationDTO updatedVaccination = vaccinationService.updateVaccination(id, vaccinationDTO);
        return ResponseEntity.ok(ApiResponse.success("Vaccination updated successfully", updatedVaccination));
    }

    @DeleteMapping("/vaccinations/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVaccination(@PathVariable Long id) {
        vaccinationService.deleteVaccination(id);
        return ResponseEntity.ok(ApiResponse.success("Vaccination deleted successfully", null));
    }
}
