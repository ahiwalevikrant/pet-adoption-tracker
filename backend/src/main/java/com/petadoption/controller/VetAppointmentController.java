package com.petadoption.controller;

import com.petadoption.dto.ApiResponse;
import com.petadoption.dto.VetAppointmentDTO;
import com.petadoption.service.VetAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VetAppointmentController {

    private final VetAppointmentService vetAppointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<ApiResponse<List<VetAppointmentDTO>>> getAllAppointments() {
        List<VetAppointmentDTO> appointments = vetAppointmentService.getAllAppointments();
        return ResponseEntity.ok(ApiResponse.success(appointments));
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<ApiResponse<VetAppointmentDTO>> getAppointmentById(@PathVariable Long id) {
        VetAppointmentDTO appointment = vetAppointmentService.getAppointmentById(id);
        return ResponseEntity.ok(ApiResponse.success(appointment));
    }

    @GetMapping("/pets/{petId}/appointments")
    public ResponseEntity<ApiResponse<List<VetAppointmentDTO>>> getAppointmentsByPetId(@PathVariable Long petId) {
        List<VetAppointmentDTO> appointments = vetAppointmentService.getAppointmentsByPetId(petId);
        return ResponseEntity.ok(ApiResponse.success(appointments));
    }

    @GetMapping("/appointments/upcoming")
    public ResponseEntity<ApiResponse<List<VetAppointmentDTO>>> getUpcomingAppointments() {
        List<VetAppointmentDTO> appointments = vetAppointmentService.getUpcomingAppointments();
        return ResponseEntity.ok(ApiResponse.success(appointments));
    }

    @PostMapping("/pets/{petId}/appointments")
    public ResponseEntity<ApiResponse<VetAppointmentDTO>> createAppointment(
            @PathVariable Long petId,
            @Valid @RequestBody VetAppointmentDTO vetAppointmentDTO) {
        vetAppointmentDTO.setPetId(petId);
        VetAppointmentDTO createdAppointment = vetAppointmentService.createAppointment(vetAppointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Appointment created successfully", createdAppointment));
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<ApiResponse<VetAppointmentDTO>> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody VetAppointmentDTO vetAppointmentDTO) {
        VetAppointmentDTO updatedAppointment = vetAppointmentService.updateAppointment(id, vetAppointmentDTO);
        return ResponseEntity.ok(ApiResponse.success("Appointment updated successfully", updatedAppointment));
    }

    @PutMapping("/appointments/{id}/status")
    public ResponseEntity<ApiResponse<VetAppointmentDTO>> updateAppointmentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        VetAppointmentDTO updatedAppointment = vetAppointmentService.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Appointment status updated successfully", updatedAppointment));
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAppointment(@PathVariable Long id) {
        vetAppointmentService.deleteAppointment(id);
        return ResponseEntity.ok(ApiResponse.success("Appointment deleted successfully", null));
    }
}
