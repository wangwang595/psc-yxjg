package com.wavenet.maintenance.web.query;

import lombok.Data;

import java.util.List;

@Data
public class QueryParameterObject{

    

    private String date;

    private List<String> project;

}