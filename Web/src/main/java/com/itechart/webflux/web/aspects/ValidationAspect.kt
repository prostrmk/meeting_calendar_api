package com.itechart.webflux.web.aspects

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.exceptions.NoAccessException
import com.itechart.webflux.web.util.ResponseUtil.Companion.respond
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Aspect
@Component
class ValidationAspect {

    private val log = LoggerFactory.getLogger(ValidationAspect::class.java)

    @AfterThrowing(pointcut = "execution(* com.itechart.webflux.web.*.*(..))", throwing = "e")
    internal fun sendValidationNotPassedResponse(joinPoint: JoinPoint, e: ValidationNotPassedException): ResponseEntity<*>? {
        return try {
            respond(String.format("{\"error\": \"%s\"}", e.message), 400)
        } catch (ex: Exception) {
            log.error("Cannot process validation exception: ", e)
            null
        }

    }

    @AfterThrowing(pointcut = "execution(* com.itechart.webflux.web.*.*(..))", throwing = "e")
    internal fun sendValidationNotPassedResponse(joinPoint: JoinPoint, e: NoAccessException): ResponseEntity<*>? {
        return try {
            respond(String.format("{\"error\": \"%s\"}", e.message), 403)
        } catch (ex: Exception) {
            log.error("Cannot process validation exception: ", e)
            null
        }

    }

}