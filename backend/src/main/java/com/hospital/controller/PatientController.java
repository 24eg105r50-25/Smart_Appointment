package com.hospital.controller;

<<<<<<< HEAD
import com.hospital.repository.DoctorRepository;
import com.hospital.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
=======
import com.hospital.entity.*;
import com.hospital.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4

@Controller
@RequestMapping("/patient")
public class PatientController {

<<<<<<< HEAD
    private final AppointmentService appointmentService;
    private final DoctorRepository doctorRepository;

    public PatientController(AppointmentService appointmentService, DoctorRepository doctorRepository) {
        this.appointmentService = appointmentService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("appointments", appointmentService.forPatient(auth.getName()));
=======
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
        return "patient-dashboard";
    }

    @PostMapping("/book")
<<<<<<< HEAD
    public String book(@RequestParam Long doctorId,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentTime,
                       @RequestParam(required = false, defaultValue = "") String reason,
                       Authentication auth, RedirectAttributes redirect) {
        try {
            appointmentService.book(auth.getName(), doctorId, appointmentTime, reason);
            redirect.addFlashAttribute("success", "Appointment booked.");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/patient/dashboard";
    }

    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id, Authentication auth, RedirectAttributes redirect) {
        try {
            appointmentService.cancel(auth.getName(), id);
            redirect.addFlashAttribute("success", "Appointment cancelled.");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
=======
    public String bookAppointment(@RequestParam Long doctorId, @RequestParam String date, @RequestParam String reason) {
        // Simplified booking logic
        Appointment appointment = new Appointment();
        // In a real app, we'd get the current logged-in patient
        appointment.setDoctor(doctorRepository.findById(doctorId).orElseThrow());
        appointment.setReason(reason);
        appointment.setStatus("SCHEDULED");
        appointmentRepository.save(appointment);
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
        return "redirect:/patient/dashboard";
    }
}
