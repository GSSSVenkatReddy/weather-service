package com.timeforge.weatherservice.service.openweatherapi.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherApiResponse {
    private double lat;
    private double lon;
    private List<Daily> daily;
}
