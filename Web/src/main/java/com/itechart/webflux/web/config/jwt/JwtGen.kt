package com.itechart.webflux.web.config.jwt

import com.itechart.webflux.web.core.model.entity.User
import com.itechart.webflux.web.core.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class JwtGen {


    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    fun generate(user: User): String? {

        val base = user.username?.let { userRepository!!.findUserByUsername(it).block() }
        if (base == null || user.password == null || !passwordEncoder!!.matches(user.password, base.password)) {
            return null
        }
        val claims = Jwts.claims()
                .setSubject(base.id)
        claims["username"] = base.username
        claims["role"] = base.role


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "truck-secret-key")
                .compact()
    }

}