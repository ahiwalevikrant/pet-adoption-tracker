package com.petadoption.service;

import com.petadoption.dto.PetDTO;
import com.petadoption.entity.Pet;
import com.petadoption.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PetDTO getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
        return mapToDTO(pet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetDTO> getAdoptablePets() {
        return petRepository.findByIsAdoptedFalse().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetDTO> getPetsBySpecies(String species) {
        return petRepository.findBySpecies(species).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetDTO> searchPets(String keyword) {
        return petRepository.searchPets(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO createPet(PetDTO petDTO) {
        Pet pet = mapToEntity(petDTO);
        pet.setIsAdopted(false);
        Pet savedPet = petRepository.save(pet);
        return mapToDTO(savedPet);
    }

    @Override
    public PetDTO updatePet(Long id, PetDTO petDTO) {
        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));

        existingPet.setName(petDTO.getName());
        existingPet.setSpecies(petDTO.getSpecies());
        existingPet.setBreed(petDTO.getBreed());
        existingPet.setAge(petDTO.getAge());
        existingPet.setGender(petDTO.getGender());
        existingPet.setDescription(petDTO.getDescription());
        existingPet.setImageUrl(petDTO.getImageUrl());
        existingPet.setLocation(petDTO.getLocation());
        existingPet.setOwnerName(petDTO.getOwnerName());
        existingPet.setOwnerContact(petDTO.getOwnerContact());

        if (petDTO.getIsAdopted() != null) {
            existingPet.setIsAdopted(petDTO.getIsAdopted());
        }

        Pet updatedPet = petRepository.save(existingPet);
        return mapToDTO(updatedPet);
    }

    @Override
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet not found with id: " + id);
        }
        petRepository.deleteById(id);
    }

    @Override
    public PetDTO markAsAdopted(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
        pet.setIsAdopted(true);
        Pet updatedPet = petRepository.save(pet);
        return mapToDTO(updatedPet);
    }

    private PetDTO mapToDTO(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies())
                .breed(pet.getBreed())
                .age(pet.getAge())
                .gender(pet.getGender())
                .description(pet.getDescription())
                .imageUrl(pet.getImageUrl())
                .isAdopted(pet.getIsAdopted())
                .location(pet.getLocation())
                .ownerName(pet.getOwnerName())
                .ownerContact(pet.getOwnerContact())
                .createdAt(pet.getCreatedAt())
                .updatedAt(pet.getUpdatedAt())
                .build();
    }

    private Pet mapToEntity(PetDTO petDTO) {
        return Pet.builder()
                .name(petDTO.getName())
                .species(petDTO.getSpecies())
                .breed(petDTO.getBreed())
                .age(petDTO.getAge())
                .gender(petDTO.getGender())
                .description(petDTO.getDescription())
                .imageUrl(petDTO.getImageUrl())
                .isAdopted(petDTO.getIsAdopted())
                .location(petDTO.getLocation())
                .ownerName(petDTO.getOwnerName())
                .ownerContact(petDTO.getOwnerContact())
                .build();
    }
}
