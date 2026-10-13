package com.hospital.repository;

import com.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientIdOrderByAppointmentTimeAsc(Long patientId);
    List<Appointment> findByDoctorIdOrderByAppointmentTimeAsc(Long doctorId);
    boolean existsByDoctorIdAndAppointmentTimeAndStatus(Long doctorId, LocalDateTime time, String status);
=======
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
}
