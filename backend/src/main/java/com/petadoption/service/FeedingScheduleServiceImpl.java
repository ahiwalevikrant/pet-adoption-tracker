package com.petadoption.service;

import com.petadoption.dto.FeedingScheduleDTO;
import com.petadoption.entity.FeedingSchedule;
import com.petadoption.entity.Pet;
import com.petadoption.repository.FeedingScheduleRepository;
import com.petadoption.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedingScheduleServiceImpl implements FeedingScheduleService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FeedingScheduleDTO> getAllFeedingSchedules() {
        return feedingScheduleRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedingScheduleDTO> getFeedingSchedulesByPetId(Long petId) {
        return feedingScheduleRepository.findByPetId(petId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FeedingScheduleDTO getFeedingScheduleById(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feeding schedule not found with id: " + id));
        return mapToDTO(feedingSchedule);
    }

    @Override
    public FeedingScheduleDTO createFeedingSchedule(FeedingScheduleDTO feedingScheduleDTO) {
        Pet pet = petRepository.findById(feedingScheduleDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + feedingScheduleDTO.getPetId()));

        FeedingSchedule feedingSchedule = mapToEntity(feedingScheduleDTO, pet);
        FeedingSchedule savedSchedule = feedingScheduleRepository.save(feedingSchedule);
        return mapToDTO(savedSchedule);
    }

    @Override
    public FeedingScheduleDTO updateFeedingSchedule(Long id, FeedingScheduleDTO feedingScheduleDTO) {
        FeedingSchedule existingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feeding schedule not found with id: " + id));

        existingSchedule.setFoodType(feedingScheduleDTO.getFoodType());
        existingSchedule.setPortionSize(feedingScheduleDTO.getPortionSize());
        existingSchedule.setFeedingTime(feedingScheduleDTO.getFeedingTime());
        existingSchedule.setFrequency(feedingScheduleDTO.getFrequency());
        existingSchedule.setNotes(feedingScheduleDTO.getNotes());

        FeedingSchedule updatedSchedule = feedingScheduleRepository.save(existingSchedule);
        return mapToDTO(updatedSchedule);
    }

    @Override
    public void deleteFeedingSchedule(Long id) {
        if (!feedingScheduleRepository.existsById(id)) {
            throw new RuntimeException("Feeding schedule not found with id: " + id);
        }
        feedingScheduleRepository.deleteById(id);
    }

    private FeedingScheduleDTO mapToDTO(FeedingSchedule feedingSchedule) {
        return FeedingScheduleDTO.builder()
                .id(feedingSchedule.getId())
                .petId(feedingSchedule.getPet().getId())
                .petName(feedingSchedule.getPet().getName())
                .foodType(feedingSchedule.getFoodType())
                .portionSize(feedingSchedule.getPortionSize())
                .feedingTime(feedingSchedule.getFeedingTime())
                .frequency(feedingSchedule.getFrequency())
                .notes(feedingSchedule.getNotes())
                .createdAt(feedingSchedule.getCreatedAt())
                .updatedAt(feedingSchedule.getUpdatedAt())
                .build();
    }

    private FeedingSchedule mapToEntity(FeedingScheduleDTO feedingScheduleDTO, Pet pet) {
        return FeedingSchedule.builder()
                .pet(pet)
                .foodType(feedingScheduleDTO.getFoodType())
                .portionSize(feedingScheduleDTO.getPortionSize())
                .feedingTime(feedingScheduleDTO.getFeedingTime())
                .frequency(feedingScheduleDTO.getFrequency())
                .notes(feedingScheduleDTO.getNotes())
                .build();
    }
}
