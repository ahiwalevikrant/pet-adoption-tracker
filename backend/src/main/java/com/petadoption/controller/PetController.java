package com.petadoption.controller;

import com.petadoption.dto.ApiResponse;
import com.petadoption.dto.PetDTO;
import com.petadoption.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PetDTO>>> getAllPets() {
        List<PetDTO> pets = petService.getAllPets();
        return ResponseEntity.ok(ApiResponse.success(pets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PetDTO>> getPetById(@PathVariable Long id) {
        PetDTO pet = petService.getPetById(id);
        return ResponseEntity.ok(ApiResponse.success(pet));
    }

    @GetMapping("/adoptable")
    public ResponseEntity<ApiResponse<List<PetDTO>>> getAdoptablePets() {
        List<PetDTO> pets = petService.getAdoptablePets();
        return ResponseEntity.ok(ApiResponse.success(pets));
    }

    @GetMapping("/species/{species}")
    public ResponseEntity<ApiResponse<List<PetDTO>>> getPetsBySpecies(@PathVariable String species) {
        List<PetDTO> pets = petService.getPetsBySpecies(species);
        return ResponseEntity.ok(ApiResponse.success(pets));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PetDTO>>> searchPets(@RequestParam String keyword) {
        List<PetDTO> pets = petService.searchPets(keyword);
        return ResponseEntity.ok(ApiResponse.success(pets));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PetDTO>> createPet(@Valid @RequestBody PetDTO petDTO) {
        PetDTO createdPet = petService.createPet(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Pet created successfully", createdPet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PetDTO>> updatePet(@PathVariable Long id, 
                                                          @Valid @RequestBody PetDTO petDTO) {
        PetDTO updatedPet = petService.updatePet(id, petDTO);
        return ResponseEntity.ok(ApiResponse.success("Pet updated successfully", updatedPet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok(ApiResponse.success("Pet deleted successfully", null));
    }

    @PutMapping("/{id}/adopt")
    public ResponseEntity<ApiResponse<PetDTO>> markAsAdopted(@PathVariable Long id) {
        PetDTO pet = petService.markAsAdopted(id);
        return ResponseEntity.ok(ApiResponse.success("Pet marked as adopted", pet));
    }
}
