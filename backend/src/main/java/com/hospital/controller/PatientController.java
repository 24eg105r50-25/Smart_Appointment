package com.hospital.controller;

import com.hospital.repository.DoctorRepository;
import com.hospital.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/patient")
public class PatientController {

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
        return "patient-dashboard";
    }

    @PostMapping("/book")
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
        return "redirect:/patient/dashboard";
    }
}
