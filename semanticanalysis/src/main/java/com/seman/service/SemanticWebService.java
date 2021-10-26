package com.seman.service;

import com.seman.projhandle.ProjectDetails;
import com.seman.projhandle.ProjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class SemanticWebService {
    @Autowired
    ProjectHandler projectHandler;

    public void handleProjectInformations(List<String> projects) {
        List<ProjectDetails> processedProjectsList = new ArrayList <>();
        projects.forEach(project -> processedProjectsList.add(projectHandler.getProjectDetails(project)));
        persistProjectDetailsToBackoffice(processedProjectsList);
    }

    public ResponseEntity<HttpStatus> persistProjectDetailsToBackoffice(List <ProjectDetails> projectsList) {
        //httpCall to BO with List of
        return ResponseEntity.ok(OK);
    }
}
