package com.travelworld.api.service;

import com.travelworld.api.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final long expiration;
    public JwtService(JwtEncoder jwtEncoder, @Value("${app.jwt.expiration:3600000}") long exxpiration) {
        this.jwtEncoder = jwtEncoder;
        this.expiration = exxpiration;
    }

    public String generarToken(Usuario usuario){
        Instant ahora = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("travelworld")
                .issuedAt(ahora)
                .expiresAt(ahora.plusMillis(expiration))
                .subject(usuario.getCorreo())
                .claim("nombre", usuario.getNombre())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
