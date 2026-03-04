package com.petadoption.service;

import com.petadoption.dto.VaccinationDTO;

import java.util.List;

public interface VaccinationService {
    
    List<VaccinationDTO> getAllVaccinations();
    
    List<VaccinationDTO> getVaccinationsByPetId(Long petId);
    
    List<VaccinationDTO> getUpcomingVaccinations();
    
    VaccinationDTO getVaccinationById(Long id);
    
    VaccinationDTO createVaccination(VaccinationDTO vaccinationDTO);
    
    VaccinationDTO updateVaccination(Long id, VaccinationDTO vaccinationDTO);
    
    void deleteVaccination(Long id);
}
