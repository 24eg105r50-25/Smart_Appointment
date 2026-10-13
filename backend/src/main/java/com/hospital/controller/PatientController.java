package com.hospital.controller;

import com.hospital.service.AppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {


private final AppointmentService appointmentService;

public PatientController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
}

@GetMapping("/dashboard")
public String dashboard(Authentication auth, Model model) {
    model.addAttribute(
        "appointments",
        appointmentService.forPatient(auth.getName())
    );

    return "patient-dashboard";
}


}
