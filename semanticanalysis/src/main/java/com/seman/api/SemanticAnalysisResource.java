package com.seman.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seman.projhandle.ProjectDetails;
import java.util.ArrayList;
import java.util.List;

import com.seman.service.CrawlerRequestBody;
import com.seman.service.SemanticWebService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class SemanticAnalysisResource {

    @Autowired
    private SemanticWebService semanticWebService;

    @GetMapping
    @RequestMapping(value = "/crawl", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    ResponseEntity <String> crawlProject(@RequestBody CrawlerRequestBody projects) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        List <ProjectDetails> processingResult = semanticWebService.handleProjectInformations(projects.getProjects());
        try {
            log.info("==============Processing result============\n{}", objectMapper.writeValueAsString(processingResult));
            return ResponseEntity.ok(objectMapper.writeValueAsString(processingResult));
        } catch (JsonProcessingException e) {
            log.error("===============Processing result failed==============\n", e);
        }
        return ResponseEntity.ok("Processing Failed!");
    }
}
