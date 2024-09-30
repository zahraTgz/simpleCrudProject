package com.isc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class AccountAspect {


    @Before(value = " execution (* com.isc.controller.AccountController.*(..)) ")
    public void beforeCallControllerAdvice(JoinPoint joinPoint) {
        log.info("Request to " + joinPoint.getSignature() + " started at " + new Date());

    }

    @After(value = " execution (* com.isc.controller.AccountController.*(..)) ")
    public void afterCallControllerAdvice(JoinPoint joinPoint) {
        log.info("Request to " + joinPoint.getSignature() + " ended at " + new Date());

    }

}
