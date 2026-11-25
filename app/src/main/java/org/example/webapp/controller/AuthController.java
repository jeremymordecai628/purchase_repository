package com.modex.webapp.api;


import com.modex.webapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest request) {

        try {
            LoginResponse response = authService.login(request);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }
    }
}
