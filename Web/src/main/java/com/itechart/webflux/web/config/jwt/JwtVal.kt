package com.itechart.webflux.web.config.jwt

import com.itechart.webflux.web.core.model.entity.User
import com.itechart.webflux.web.core.model.entity.UserRole
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class JwtVal {

    private val log = LoggerFactory.getLogger(JwtVal::class.java)

    fun validate(token: String): User? {

        var jwtUser: User? = null
        try {
            val body = Jwts.parser().setSigningKey("truck-secret-key").parseClaimsJws(token).body
            if (body != null) {
                val username = body.get("username", String::class.java)
                val role = UserRole.valueOf(body.get("role", String::class.java))
                jwtUser = User(username, role)
            }
        } catch (e: MalformedJwtException) {
            log.info("Empty or invalid token: " + e.message)
        } catch (e: SignatureException) {
            log.warn("Someone tried to change token in header. Token value: $token")
        } catch (e: Exception) {
            log.warn("Exception in token validation(Maybe, no such user): ", e)
        }

        return jwtUser
    }

}