package com.petadoption.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "vet_appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VetAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @Column(length = 100)
    private String veterinarian;

    @Column(length = 100)
    private String clinic;

    @Column(length = 255)
    private String reason;

    @Column(length = 20)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
