package com.petadoption.repository;

import com.petadoption.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    
    List<Pet> findByIsAdoptedFalse();
    
    List<Pet> findBySpecies(String species);
    
    List<Pet> findByLocationContainingIgnoreCase(String location);
    
    @Query("SELECT p FROM Pet p WHERE p.isAdopted = false AND " +
           "(p.name LIKE %:keyword% OR p.breed LIKE %:keyword% OR p.species LIKE %:keyword%)")
    List<Pet> searchPets(String keyword);
}
