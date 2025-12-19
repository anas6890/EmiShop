package ma.ac.emi.ginf.emishop.controller;

import ma.ac.emi.ginf.emishop.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService authService;

    public AuthController(UserService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password) {
        return authService.register(email, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }
}

