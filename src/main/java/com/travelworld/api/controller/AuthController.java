package com.travelworld.api.controller;

import com.travelworld.api.dto.RegistroRequest;
import com.travelworld.api.entity.Usuario;
import com.travelworld.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public  AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registrar (@Valid @RequestBody RegistroRequest datos){
        Usuario usuario = authService.registrar(
                datos.nombre(),
                datos.correo(),
                datos.password()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "mensaje", "Usuario registrado correctamente",
                        "correo", usuario.getCorreo()
                ));
    }
}
