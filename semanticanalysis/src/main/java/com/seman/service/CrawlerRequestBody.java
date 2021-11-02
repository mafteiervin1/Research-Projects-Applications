package com.seman.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
@JsonSerialize
public class CrawlerRequestBody {
    private List <String> projects;

    public List <String> getProjects() {
        return projects;
    }

    public void setProjects(List <String> projects) {
        this.projects = projects;
    }

}
