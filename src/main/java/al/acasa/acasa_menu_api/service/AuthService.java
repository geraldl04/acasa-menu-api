package al.acasa.acasa_menu_api.service;

import al.acasa.acasa_menu_api.dto.*;
import al.acasa.acasa_menu_api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        String token = jwtUtil.generateToken(auth.getName());
        return LoginResponse.builder()
                .token(token)
                .username(auth.getName())
                .build();
    }
}