package com.itechart.webflux.web.config.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*


open class JwtAuthToken(var token: String?, private var username: String?, private var role: String?) : UsernamePasswordAuthenticationToken(username, role, Collections.singleton(SimpleGrantedAuthority(role))) {

    constructor(token: String?) : this(token, null, null)

    override fun getCredentials(): Any? {
        return role
    }

    override fun getPrincipal(): Any? {
        return username
    }


}