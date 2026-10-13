package com.hospital.controller;

<<<<<<< HEAD
import com.hospital.service.RegistrationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
=======
import com.hospital.entity.User;
import com.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4

@Controller
public class AuthController {

<<<<<<< HEAD
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
=======
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
<<<<<<< HEAD
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role,
                               @RequestParam(required = false) String fullName,
                               @RequestParam(required = false) String specialization,
                               RedirectAttributes redirect) {
        try {
            registrationService.register(username, email, password, role, fullName, specialization);
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
        return "redirect:/login?registered";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        String role = auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        model.addAttribute("username", auth.getName());
        model.addAttribute("role", role);
=======
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam User.Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
        return "dashboard";
    }
}
