package com.petadoption.service;

import com.petadoption.dto.FeedingScheduleDTO;

import java.util.List;

public interface FeedingScheduleService {
    
    List<FeedingScheduleDTO> getAllFeedingSchedules();
    
    List<FeedingScheduleDTO> getFeedingSchedulesByPetId(Long petId);
    
    FeedingScheduleDTO getFeedingScheduleById(Long id);
    
    FeedingScheduleDTO createFeedingSchedule(FeedingScheduleDTO feedingScheduleDTO);
    
    FeedingScheduleDTO updateFeedingSchedule(Long id, FeedingScheduleDTO feedingScheduleDTO);
    
    void deleteFeedingSchedule(Long id);
}
