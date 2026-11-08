package com.travelworld.api.controller;

import com.travelworld.api.dto.LoginRequest;
import com.travelworld.api.dto.RegistroRequest;
import com.travelworld.api.entity.Usuario;
import com.travelworld.api.service.AuthService;
import com.travelworld.api.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public  AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
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

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login (@Valid @RequestBody LoginRequest datos){
        try {
            Usuario usuario = authService.login(datos.correo(), datos.password());
            String token = jwtService.generarToken(usuario);

            return ResponseEntity.ok(
                    Map.of(
                            "Mensaje", "Inicio de sesion correcto",
                            "token", token,
                            "tipo", "Bearer",
                            "correo", usuario.getCorreo(),
                            "nombre", usuario.getNombre()
                    )
            );
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("Mensaje", "Correo o contraseña no encontrados"));
        }
    }
}
