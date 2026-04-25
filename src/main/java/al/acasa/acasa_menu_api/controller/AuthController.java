package al.acasa.acasa_menu_api.controller;

import al.acasa.acasa_menu_api.dto.*;
import al.acasa.acasa_menu_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        String passi = new BCryptPasswordEncoder().encode("admin123");

        return ResponseEntity.ok(authService.login(request));
    }
}