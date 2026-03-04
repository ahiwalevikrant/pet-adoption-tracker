package com.petadoption.service;

import com.petadoption.dto.PetDTO;

import java.util.List;

public interface PetService {
    
    List<PetDTO> getAllPets();
    
    PetDTO getPetById(Long id);
    
    List<PetDTO> getAdoptablePets();
    
    List<PetDTO> getPetsBySpecies(String species);
    
    List<PetDTO> searchPets(String keyword);
    
    PetDTO createPet(PetDTO petDTO);
    
    PetDTO updatePet(Long id, PetDTO petDTO);
    
    void deletePet(Long id);
    
    PetDTO markAsAdopted(Long id);
}
