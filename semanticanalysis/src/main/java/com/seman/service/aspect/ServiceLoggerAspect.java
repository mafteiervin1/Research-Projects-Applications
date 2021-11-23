package com.seman.service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Aspect
@Configuration
public class ServiceLoggerAspect {

    @After(value = "execution(public * com.seman.service.SemanticWebService.handleProjectInformations(*))")
    public void after(JoinPoint joinPoint) {
        log.info("backoffice called with [{}] ", joinPoint.getArgs());
    }

}
