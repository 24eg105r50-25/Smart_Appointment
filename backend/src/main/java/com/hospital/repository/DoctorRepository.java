package com.hospital.repository;

import com.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
<<<<<<< HEAD
    Optional<Doctor> findByUserUsername(String username);
=======
    Optional<Doctor> findByUserId(Long userId);
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
}
