package com.hospital.controller;

import com.hospital.service.AppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final AppointmentService appointmentService;

    public DoctorController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        model.addAttribute("appointments", appointmentService.forDoctor(auth.getName()));
        return "doctor-dashboard";
    }

    @PostMapping("/complete/{id}")
    public String complete(@PathVariable Long id, Authentication auth, RedirectAttributes redirect) {
        try {
            appointmentService.complete(auth.getName(), id);
            redirect.addFlashAttribute("success", "Appointment marked as completed.");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/doctor/dashboard";
    }
}
