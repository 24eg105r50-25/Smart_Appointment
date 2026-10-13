package com.hospital.controller;

<<<<<<< HEAD
import com.hospital.service.AppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
=======
import com.hospital.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4

@Controller
@RequestMapping("/doctor")
public class DoctorController {

<<<<<<< HEAD
    private final AppointmentService appointmentService;

    public DoctorController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        model.addAttribute("appointments", appointmentService.forDoctor(auth.getName()));
=======
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
        return "doctor-dashboard";
    }

    @PostMapping("/complete/{id}")
<<<<<<< HEAD
    public String complete(@PathVariable Long id, Authentication auth, RedirectAttributes redirect) {
        try {
            appointmentService.complete(auth.getName(), id);
            redirect.addFlashAttribute("success", "Appointment marked as completed.");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
=======
    public String completeAppointment(@PathVariable Long id) {
        // logic to mark as completed
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
        return "redirect:/doctor/dashboard";
    }
}
