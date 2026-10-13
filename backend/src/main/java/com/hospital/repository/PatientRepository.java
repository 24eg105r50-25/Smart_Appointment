package com.hospital.repository;

import com.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
<<<<<<< HEAD
    Optional<Patient> findByUserUsername(String username);
=======
    Optional<Patient> findByUserId(Long userId);
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
}
