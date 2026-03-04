package com.petadoption.repository;

import com.petadoption.entity.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {
    
    List<FeedingSchedule> findByPetId(Long petId);
    
    List<FeedingSchedule> findByFrequency(String frequency);
}
