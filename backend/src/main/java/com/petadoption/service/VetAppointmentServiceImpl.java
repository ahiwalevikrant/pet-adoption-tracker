package com.petadoption.service;

import com.petadoption.dto.VetAppointmentDTO;
import com.petadoption.entity.Pet;
import com.petadoption.entity.VetAppointment;
import com.petadoption.repository.PetRepository;
import com.petadoption.repository.VetAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VetAppointmentServiceImpl implements VetAppointmentService {

    private final VetAppointmentRepository vetAppointmentRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VetAppointmentDTO> getAllAppointments() {
        return vetAppointmentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VetAppointmentDTO> getAppointmentsByPetId(Long petId) {
        return vetAppointmentRepository.findByPetId(petId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VetAppointmentDTO> getUpcomingAppointments() {
        return vetAppointmentRepository.findByAppointmentDateAfterAndStatus(LocalDateTime.now(), "SCHEDULED").stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VetAppointmentDTO getAppointmentById(Long id) {
        VetAppointment appointment = vetAppointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        return mapToDTO(appointment);
    }

    @Override
    public VetAppointmentDTO createAppointment(VetAppointmentDTO vetAppointmentDTO) {
        Pet pet = petRepository.findById(vetAppointmentDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + vetAppointmentDTO.getPetId()));

        VetAppointment appointment = mapToEntity(vetAppointmentDTO, pet);
        if (appointment.getStatus() == null) {
            appointment.setStatus("SCHEDULED");
        }
        VetAppointment savedAppointment = vetAppointmentRepository.save(appointment);
        return mapToDTO(savedAppointment);
    }

    @Override
    public VetAppointmentDTO updateAppointment(Long id, VetAppointmentDTO vetAppointmentDTO) {
        VetAppointment existingAppointment = vetAppointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        existingAppointment.setAppointmentDate(vetAppointmentDTO.getAppointmentDate());
        existingAppointment.setVeterinarian(vetAppointmentDTO.getVeterinarian());
        existingAppointment.setClinic(vetAppointmentDTO.getClinic());
        existingAppointment.setReason(vetAppointmentDTO.getReason());
        existingAppointment.setStatus(vetAppointmentDTO.getStatus());
        existingAppointment.setNotes(vetAppointmentDTO.getNotes());

        VetAppointment updatedAppointment = vetAppointmentRepository.save(existingAppointment);
        return mapToDTO(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!vetAppointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        vetAppointmentRepository.deleteById(id);
    }

    @Override
    public VetAppointmentDTO updateAppointmentStatus(Long id, String status) {
        VetAppointment appointment = vetAppointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        
        appointment.setStatus(status);
        VetAppointment updatedAppointment = vetAppointmentRepository.save(appointment);
        return mapToDTO(updatedAppointment);
    }

    private VetAppointmentDTO mapToDTO(VetAppointment appointment) {
        return VetAppointmentDTO.builder()
                .id(appointment.getId())
                .petId(appointment.getPet().getId())
                .petName(appointment.getPet().getName())
                .appointmentDate(appointment.getAppointmentDate())
                .veterinarian(appointment.getVeterinarian())
                .clinic(appointment.getClinic())
                .reason(appointment.getReason())
                .status(appointment.getStatus())
                .notes(appointment.getNotes())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .build();
    }

    private VetAppointment mapToEntity(VetAppointmentDTO vetAppointmentDTO, Pet pet) {
        return VetAppointment.builder()
                .pet(pet)
                .appointmentDate(vetAppointmentDTO.getAppointmentDate())
                .veterinarian(vetAppointmentDTO.getVeterinarian())
                .clinic(vetAppointmentDTO.getClinic())
                .reason(vetAppointmentDTO.getReason())
                .status(vetAppointmentDTO.getStatus())
                .notes(vetAppointmentDTO.getNotes())
                .build();
    }
}
