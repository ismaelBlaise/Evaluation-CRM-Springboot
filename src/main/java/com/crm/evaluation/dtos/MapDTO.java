package com.crm.evaluation.dtos;

import java.util.Map;


import lombok.Data;

@Data
public class MapDTO {
    
    private Map<String,Integer> dataInteger;
    private Map<String,Double> dataDouble;
    
}
