package com.example.friendface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("login")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final LoginService loginService;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, LoginService loginService){
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.loginService = loginService;
    }


    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = loginService.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }


}
