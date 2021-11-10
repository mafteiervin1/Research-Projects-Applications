package com.seman.projhandle.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Aspect
@Configuration
public class ProcessorLoggerAspect {

    @AfterReturning(value = "execution(public * com.seman.projhandle.processor.Processor.getPropertyValue(java.lang.String))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        log.info("{} return value {}", joinPoint.getSignature(), result);
    }

}
