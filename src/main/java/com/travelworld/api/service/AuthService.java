package com.travelworld.api.service;

import com.travelworld.api.entity.Usuario;
import com.travelworld.api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(String nombre, String correo, String password){
        String correoNormalizado = correo.trim().toLowerCase();

        if(usuarioRepository.existsByCorreo(correoNormalizado)){
            throw new IllegalArgumentException("Este correo ya se encuentra registrado");
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre.trim());
        usuario.setCorreo(correoNormalizado);
        usuario.setPassword(passwordEncoder.encode(password));

        return usuarioRepository.save(usuario);
    }
}
