package com.timeforge.weatherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyWeather {
    private String date;
    private double lowTemperatute;
    private double highTemperature;
    private double humidity;
    private double precipitationPercentage;
    private String descriptiveCondition;
}
