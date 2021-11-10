package com.seman.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
@JsonSerialize
public class CrawlerRequestBody {

    private List <String> projects;

}
