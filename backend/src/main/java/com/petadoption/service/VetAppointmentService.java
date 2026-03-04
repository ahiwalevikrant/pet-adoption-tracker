package com.petadoption.service;

import com.petadoption.dto.VetAppointmentDTO;

import java.util.List;

public interface VetAppointmentService {
    
    List<VetAppointmentDTO> getAllAppointments();
    
    List<VetAppointmentDTO> getAppointmentsByPetId(Long petId);
    
    List<VetAppointmentDTO> getUpcomingAppointments();
    
    VetAppointmentDTO getAppointmentById(Long id);
    
    VetAppointmentDTO createAppointment(VetAppointmentDTO vetAppointmentDTO);
    
    VetAppointmentDTO updateAppointment(Long id, VetAppointmentDTO vetAppointmentDTO);
    
    void deleteAppointment(Long id);
    
    VetAppointmentDTO updateAppointmentStatus(Long id, String status);
}
