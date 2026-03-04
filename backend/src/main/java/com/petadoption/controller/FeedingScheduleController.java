package com.petadoption.controller;

import com.petadoption.dto.ApiResponse;
import com.petadoption.dto.FeedingScheduleDTO;
import com.petadoption.service.FeedingScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FeedingScheduleController {

    private final FeedingScheduleService feedingScheduleService;

    @GetMapping("/feeding-schedules")
    public ResponseEntity<ApiResponse<List<FeedingScheduleDTO>>> getAllFeedingSchedules() {
        List<FeedingScheduleDTO> schedules = feedingScheduleService.getAllFeedingSchedules();
        return ResponseEntity.ok(ApiResponse.success(schedules));
    }

    @GetMapping("/feeding-schedules/{id}")
    public ResponseEntity<ApiResponse<FeedingScheduleDTO>> getFeedingScheduleById(@PathVariable Long id) {
        FeedingScheduleDTO schedule = feedingScheduleService.getFeedingScheduleById(id);
        return ResponseEntity.ok(ApiResponse.success(schedule));
    }

    @GetMapping("/pets/{petId}/feeding-schedules")
    public ResponseEntity<ApiResponse<List<FeedingScheduleDTO>>> getFeedingSchedulesByPetId(@PathVariable Long petId) {
        List<FeedingScheduleDTO> schedules = feedingScheduleService.getFeedingSchedulesByPetId(petId);
        return ResponseEntity.ok(ApiResponse.success(schedules));
    }

    @PostMapping("/pets/{petId}/feeding-schedules")
    public ResponseEntity<ApiResponse<FeedingScheduleDTO>> createFeedingSchedule(
            @PathVariable Long petId,
            @Valid @RequestBody FeedingScheduleDTO feedingScheduleDTO) {
        feedingScheduleDTO.setPetId(petId);
        FeedingScheduleDTO createdSchedule = feedingScheduleService.createFeedingSchedule(feedingScheduleDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Feeding schedule created successfully", createdSchedule));
    }

    @PutMapping("/feeding-schedules/{id}")
    public ResponseEntity<ApiResponse<FeedingScheduleDTO>> updateFeedingSchedule(
            @PathVariable Long id,
            @Valid @RequestBody FeedingScheduleDTO feedingScheduleDTO) {
        FeedingScheduleDTO updatedSchedule = feedingScheduleService.updateFeedingSchedule(id, feedingScheduleDTO);
        return ResponseEntity.ok(ApiResponse.success("Feeding schedule updated successfully", updatedSchedule));
    }

    @DeleteMapping("/feeding-schedules/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFeedingSchedule(@PathVariable Long id) {
        feedingScheduleService.deleteFeedingSchedule(id);
        return ResponseEntity.ok(ApiResponse.success("Feeding schedule deleted successfully", null));
    }
}
