package com.itechart.webflux.web.config.jwt;

import com.itechart.webflux.web.core.model.entity.User;
import com.itechart.webflux.web.core.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtGen {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String generate(User user) {

        User base = userRepository.findUserByUsername(user.getUsername()).block();
        if (base == null || user.getPassword() == null || !passwordEncoder.matches(user.getPassword(), base.getPassword())) {
            return null;
        }
        Claims claims = Jwts.claims()
                .setSubject(base.getId());
        claims.put("username", String.valueOf(base.getUsername()));
        claims.put("role", base.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "truck-secret-key")
                .compact();
    }

}
