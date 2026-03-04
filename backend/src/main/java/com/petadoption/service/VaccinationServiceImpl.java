package com.petadoption.service;

import com.petadoption.dto.VaccinationDTO;
import com.petadoption.entity.Pet;
import com.petadoption.entity.Vaccination;
import com.petadoption.repository.PetRepository;
import com.petadoption.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VaccinationDTO> getAllVaccinations() {
        return vaccinationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VaccinationDTO> getVaccinationsByPetId(Long petId) {
        return vaccinationRepository.findByPetId(petId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VaccinationDTO> getUpcomingVaccinations() {
        return vaccinationRepository.findByNextDueDateBefore(LocalDate.now().plusDays(30)).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VaccinationDTO getVaccinationById(Long id) {
        Vaccination vaccination = vaccinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccination not found with id: " + id));
        return mapToDTO(vaccination);
    }

    @Override
    public VaccinationDTO createVaccination(VaccinationDTO vaccinationDTO) {
        Pet pet = petRepository.findById(vaccinationDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + vaccinationDTO.getPetId()));

        Vaccination vaccination = mapToEntity(vaccinationDTO, pet);
        Vaccination savedVaccination = vaccinationRepository.save(vaccination);
        return mapToDTO(savedVaccination);
    }

    @Override
    public VaccinationDTO updateVaccination(Long id, VaccinationDTO vaccinationDTO) {
        Vaccination existingVaccination = vaccinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccination not found with id: " + id));

        existingVaccination.setName(vaccinationDTO.getName());
        existingVaccination.setDateAdministered(vaccinationDTO.getDateAdministered());
        existingVaccination.setNextDueDate(vaccinationDTO.getNextDueDate());
        existingVaccination.setVeterinarian(vaccinationDTO.getVeterinarian());
        existingVaccination.setNotes(vaccinationDTO.getNotes());

        Vaccination updatedVaccination = vaccinationRepository.save(existingVaccination);
        return mapToDTO(updatedVaccination);
    }

    @Override
    public void deleteVaccination(Long id) {
        if (!vaccinationRepository.existsById(id)) {
            throw new RuntimeException("Vaccination not found with id: " + id);
        }
        vaccinationRepository.deleteById(id);
    }

    private VaccinationDTO mapToDTO(Vaccination vaccination) {
        return VaccinationDTO.builder()
                .id(vaccination.getId())
                .petId(vaccination.getPet().getId())
                .petName(vaccination.getPet().getName())
                .name(vaccination.getName())
                .dateAdministered(vaccination.getDateAdministered())
                .nextDueDate(vaccination.getNextDueDate())
                .veterinarian(vaccination.getVeterinarian())
                .notes(vaccination.getNotes())
                .createdAt(vaccination.getCreatedAt())
                .updatedAt(vaccination.getUpdatedAt())
                .build();
    }

    private Vaccination mapToEntity(VaccinationDTO vaccinationDTO, Pet pet) {
        return Vaccination.builder()
                .pet(pet)
                .name(vaccinationDTO.getName())
                .dateAdministered(vaccinationDTO.getDateAdministered())
                .nextDueDate(vaccinationDTO.getNextDueDate())
                .veterinarian(vaccinationDTO.getVeterinarian())
                .notes(vaccinationDTO.getNotes())
                .build();
    }
}
