package com.seman.api;

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
    @RequestMapping(value = "/dummy", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    ResponseEntity<String> dummyApi(@RequestBody CrawlerRequestBody projects) {
        semanticWebService.handleProjectInformations(projects.getProjects());
        return ResponseEntity.ok("Dummy api.");
    }
}
