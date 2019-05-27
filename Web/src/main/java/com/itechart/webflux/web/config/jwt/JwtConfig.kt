package com.itechart.webflux.web.config.jwt

import com.itechart.webflux.web.filter.JwtAuthFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
open class JwtConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val authenticationProvider: JwtAuthProvider? = null

    @Autowired
    private val entryPoint: JwtAuthenticationEntryPoint? = null

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(authenticationProvider?.let { listOf<AuthenticationProvider>(it) })
    }

    @Bean
    open fun authTokenFilter(): JwtAuthFilter {

        val filter = JwtAuthFilter()
        filter.setAuthenticationManager(authenticationManager())
        filter.setAuthenticationSuccessHandler(JwtSuccessHandler())
        return filter

    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("GET")
        config.addAllowedMethod("PUT")
        config.addAllowedMethod("POST")
        config.allowCredentials = true
        source.registerCorsConfiguration("/**", config)
        http.csrf().disable()
                .authorizeRequests().antMatchers("**/**").authenticated()
                .and().cors().configurationSource(source).and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        http.headers().cacheControl()
        http.cors()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


}