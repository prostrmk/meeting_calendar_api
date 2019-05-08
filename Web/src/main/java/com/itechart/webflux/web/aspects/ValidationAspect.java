package com.itechart.webflux.web.aspects;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.itechart.webflux.web.util.ResponseUtil.respond;

@Aspect
@Component
public class ValidationAspect {

    private Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @AfterThrowing(pointcut = "execution(* com.itechart.webflux.web.*.*(..))", throwing = "e")
    ResponseEntity sendValidationNotPassedResponse(JoinPoint joinPoint, ValidationNotPassedException e) {
        try {
            return respond(String.format("{\"error\": \"%s\"}", e.getMessage()), 400);
        } catch (Exception ex) {
            LOGGER.error("Cannot process validation exception: ", e);
            return null;
        }
    }

}
