package com.itechart.webflux.web.config.jwt

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail : UserDetails {

    private val username: String?
    private val token: String?
    private val id: String?
    private val authorities: Collection<GrantedAuthority>

    constructor(username: String?, token: String?, id: String?, authorities: Collection<GrantedAuthority>) {
        this.username = username
        this.token = token
        this.id = id
        this.authorities = authorities
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getToken(): String? {
        return token
    }

    fun getId(): String? {
        return id
    }

}