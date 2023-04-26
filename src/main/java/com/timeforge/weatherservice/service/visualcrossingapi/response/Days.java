package com.timeforge.weatherservice.service.visualcrossingapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Days {
    private String datetime;
    private double tempmax;
    private double tempmin;
    private double humidity;
    private double precipprob;
    private String description;
}
