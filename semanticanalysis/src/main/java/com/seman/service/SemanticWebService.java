package com.seman.service;

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

    @Value("${backoffice.url:localhost:8090/projectjson}")
    private String backofficeUrl;

    public void handleProjectInformations(List <String> projects) {
        List <ProjectDetails> processedProjectsList = new ArrayList <>();
        projects.forEach(project -> processedProjectsList.add(projectHandler.getProjectDetails(project)));
        persistProjectDetailsToBackoffice(processedProjectsList);
    }

    private ResponseEntity <HttpStatus> persistProjectDetailsToBackoffice(List <ProjectDetails> projectsList) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> response = restTemplate.postForEntity(backofficeUrl, new HttpEntity <>(projectsList.get(0)), String.class );
        return ResponseEntity.ok(OK);
    }
}
