package com.seman.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seman.projhandle.ProjectDetails;
import com.seman.projhandle.ProjectHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Service
@Log4j2
public class SemanticWebService {
    @Autowired
    ProjectHandler projectHandler;

    @Value("${backoffice.url:http://backoffice:8090/projectjson/many}")
    private String backofficeUrl;

    public void handleProjectInformations(List <String> projects) {
        List <ProjectDetails> processedProjectsList = new ArrayList <>();
        projects.forEach(project -> processedProjectsList.add(projectHandler.getProjectDetails(project)));
        persistProjectDetailsToBackoffice(processedProjectsList);
    }

    private ResponseEntity <String> persistProjectDetailsToBackoffice(List <ProjectDetails> projectsList) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ResponseEntity<String> response = null;
        try {
            log.info("==============Request to BO============\n{}", objectMapper.writeValueAsString(projectsList));
            response = restTemplate.postForEntity(backofficeUrl,
                new HttpEntity <>(objectMapper.writeValueAsString(projectsList), headers), String.class);
            log.info("response is null {}",response != null);
        } catch (JsonProcessingException e) {
            log.error("===============BO request failed==============\n", e);
        }
        log.info("==============Status code from BO============\n{}", response.getStatusCode());
        log.info("==============Response body from BO=============\n{}", response.getBody());
        return ResponseEntity.ok(response.getBody());
    }
}
