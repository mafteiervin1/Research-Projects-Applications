package com.seman.projhandle;

import lombok.extern.log4j.Log4j2;

@Log4j2
public aspect ProcessorLoggerAj {
    pointcut getPropertyValue() :
        call(public * Processor+.getPropertyValue(java.lang.String));

    after(String project): getPropertyValue() {
        log.info(project);
    }
}
