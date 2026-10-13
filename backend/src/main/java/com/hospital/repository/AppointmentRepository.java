package com.hospital.repository;

import com.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientIdOrderByAppointmentTimeAsc(Long patientId);
    List<Appointment> findByDoctorIdOrderByAppointmentTimeAsc(Long doctorId);
    boolean existsByDoctorIdAndAppointmentTimeAndStatus(Long doctorId, LocalDateTime time, String status);
}
