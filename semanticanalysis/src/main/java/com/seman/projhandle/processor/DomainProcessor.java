package com.seman.projhandle.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DomainProcessor extends Processor {

    @Override
    public String getPropertyValue(String project) {
        log.info("DomainProcessor");
        return "Dummy domain";
    }
}