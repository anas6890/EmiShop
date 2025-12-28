package ma.ac.emi.ginf.emishop.controller;

import ma.ac.emi.ginf.emishop.Service.UserService;
import org.springframework.web.bind.annotation.*;
import ma.ac.emi.ginf.emishop.DTO.LoginRequest;
import ma.ac.emi.ginf.emishop.DTO.RegisterRequest;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService authService;

    public AuthController(UserService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest.getNom(),registerRequest.getPrenom(),registerRequest.getEmail(), registerRequest.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginrequest) {
        return authService.login(loginrequest.getEmail(), loginrequest.getPassword());
    }
}

