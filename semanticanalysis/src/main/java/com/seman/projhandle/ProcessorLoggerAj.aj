package com.seman.projhandle;

import lombok.extern.log4j.Log4j2;

@Log4j2
public aspect ProcessorLoggerAj {
    pointcut getPropertyValue() :
        call(public * com.seman.projhandle.processor.Processor.getPropertyValue(java.lang.String));

    after() returning(String fieldValue): getPropertyValue() {
        log.info("{} with value {}", thisJoinPoint.getSignature(), fieldValue);
        System.out.println(thisJoinPoint.getSignature() + "with value" + fieldValue);
    }
}
