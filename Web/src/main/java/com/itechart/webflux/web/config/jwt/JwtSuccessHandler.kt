package com.itechart.webflux.web.config.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.ArrayList
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtSuccessHandler : AuthenticationSuccessHandler {


    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, authentication: Authentication) {
        var token: String? = httpServletRequest.getHeader("Authorization")
        if (token != null) {
            token = token.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        }
        val validate = JwtVal().validate(token!!)
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(validate!!.role.name))
        val jwtAuthToken = JwtAuthToken(token, validate.username, validate.role.name)
        jwtAuthToken.details = jwtAuthToken.credentials
        SecurityContextHolder.getContext().authentication = jwtAuthToken

    }

}