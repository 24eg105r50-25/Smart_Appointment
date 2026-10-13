package com.hospital.service;

import com.hospital.entity.Appointment;
import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    public static final String SCHEDULED = "SCHEDULED";
    public static final String COMPLETED = "COMPLETED";
    public static final String CANCELLED = "CANCELLED";

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public void book(String patientUsername, Long doctorId, LocalDateTime time, String reason) {
        Patient patient = patientRepository.findByUserUsername(patientUsername)
                .orElseThrow(() -> new IllegalArgumentException("Patient profile not found."));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found."));
        if (time == null || time.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Please choose a future date and time.");
        if (appointmentRepository.existsByDoctorIdAndAppointmentTimeAndStatus(doctorId, time, SCHEDULED))
            throw new IllegalArgumentException("That time slot is already booked. Please pick another.");

        Appointment a = new Appointment();
        a.setPatient(patient);
        a.setDoctor(doctor);
        a.setAppointmentTime(time);
        a.setReason(reason);
        a.setStatus(SCHEDULED);
        appointmentRepository.save(a);
    }

    @Transactional(readOnly = true)
    public List<Appointment> forPatient(String username) {
        return patientRepository.findByUserUsername(username)
                .map(p -> appointmentRepository.findByPatientIdOrderByAppointmentTimeAsc(p.getId()))
                .orElse(List.of());
    }

    @Transactional(readOnly = true)
    public List<Appointment> forDoctor(String username) {
        return doctorRepository.findByUserUsername(username)
                .map(d -> appointmentRepository.findByDoctorIdOrderByAppointmentTimeAsc(d.getId()))
                .orElse(List.of());
    }

    @Transactional
    public void complete(String doctorUsername, Long appointmentId) {
        Appointment a = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
        if (a.getDoctor() == null || a.getDoctor().getUser() == null
                || !a.getDoctor().getUser().getUsername().equals(doctorUsername))
            throw new IllegalArgumentException("You can only complete your own appointments.");
        if (!SCHEDULED.equals(a.getStatus()))
            throw new IllegalArgumentException("Only scheduled appointments can be completed.");
        a.setStatus(COMPLETED);
        appointmentRepository.save(a);
    }

    @Transactional
    public void cancel(String patientUsername, Long appointmentId) {
        Appointment a = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
        if (a.getPatient() == null || a.getPatient().getUser() == null
                || !a.getPatient().getUser().getUsername().equals(patientUsername))
            throw new IllegalArgumentException("You can only cancel your own appointments.");
        if (!SCHEDULED.equals(a.getStatus()))
            throw new IllegalArgumentException("Only scheduled appointments can be cancelled.");
        a.setStatus(CANCELLED);
        appointmentRepository.save(a);
    }
}
