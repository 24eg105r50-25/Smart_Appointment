package com.hospital.service;

import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.entity.User;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PatientRepository patientRepository,
                               DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(String username, String email, String password, String role,
                         String fullName, String specialization) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is required.");
        if (password == null || password.length() < 6) throw new IllegalArgumentException("Password must be at least 6 characters.");
        if (userRepository.existsByUsername(username.trim())) throw new IllegalArgumentException("That username is already taken.");

        User.Role userRole;
        try {
            userRole = User.Role.valueOf(role == null ? "" : role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role.");
        }
        // Admins can never be self-registered.
        if (userRole == User.Role.ADMIN) throw new IllegalArgumentException("Invalid role.");

        String name = (fullName == null || fullName.isBlank()) ? username.trim() : fullName.trim();

        User user = new User();
        user.setUsername(username.trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userRole);
        user = userRepository.save(user);

        if (userRole == User.Role.PATIENT) {
            Patient p = new Patient();
            p.setUser(user);
            p.setFullName(name);
            patientRepository.save(p);
        } else {
            Doctor d = new Doctor();
            d.setUser(user);
            d.setFullName(name);
            d.setSpecialization((specialization == null || specialization.isBlank()) ? "General Physician" : specialization.trim());
            d.setAvailability("Mon-Fri");
            doctorRepository.save(d);
        }
    }
}
