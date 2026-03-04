package com.petadoption.repository;

import com.petadoption.entity.VetAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VetAppointmentRepository extends JpaRepository<VetAppointment, Long> {
    
    List<VetAppointment> findByPetId(Long petId);
    
    List<VetAppointment> findByStatus(String status);
    
    List<VetAppointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
    
    List<VetAppointment> findByAppointmentDateAfterAndStatus(LocalDateTime date, String status);
}
