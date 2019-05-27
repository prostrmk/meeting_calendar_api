package com.itechart.webflux.web.filter

import com.itechart.webflux.web.config.jwt.JwtAuthToken
import com.itechart.webflux.web.config.jwt.JwtVal
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthFilter : AbstractAuthenticationProcessingFilter {

    constructor(): super("/api/**")

    private var log = LoggerFactory.getLogger(JwtAuthFilter::class.java)

    @Autowired
    private val jwtVal: JwtVal? = null

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): Authentication? {
        val header = httpServletRequest.getHeader("Authorization")
        if (header == null) {
            log.warn("No token in header")
            httpServletResponse.sendError(403)
            return null
        }
        val token = JwtAuthToken(header.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
        return authenticationManager.authenticate(token)
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        super.successfulAuthentication(request, response, chain, authResult)
        chain!!.doFilter(request, response)
    }

}