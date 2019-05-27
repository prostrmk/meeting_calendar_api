package com.itechart.webflux.web.config.jwt

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class JwtAuthProvider : AbstractUserDetailsAuthenticationProvider() {

    @Autowired
    private val validator: JwtVal? = null

    @Throws(AuthenticationException::class)
    override fun additionalAuthenticationChecks(userDetails: UserDetails, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken) {
        //empty
    }

    @Throws(AuthenticationException::class)
    override fun retrieveUser(s: String, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken): UserDetails {
        val jwtAuthenticationToken = usernamePasswordAuthenticationToken as JwtAuthToken
        val token = jwtAuthenticationToken.token
        val jwtUser = token?.let { validator!!.validate(it) }

        val grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser!!.role.toString())
        return UserDetail(jwtUser.username, token, jwtUser.id, grantedAuthorities)

    }

}