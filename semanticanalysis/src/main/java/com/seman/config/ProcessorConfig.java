package com.seman.config;

import com.seman.projhandle.processor.ConcreteProcessor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class ProcessorConfig {

    public Map <String, ConcreteProcessor> processors;
    private String FILE_PATH = "processor/processor-configuration.yaml";

//    public ProcessorConfig () {
//        this.processors = new HashMap <String, ConcreteProcessor>(loadResourceFile());
//    }

    public <T> T loadResourceFile() {
        Yaml yaml = new Yaml(new Constructor(ProcessorConfig.class));
        return
            yaml
                .load(this.getClass()
                          .getClassLoader()
                          .getResourceAsStream(FILE_PATH));
    }
}
