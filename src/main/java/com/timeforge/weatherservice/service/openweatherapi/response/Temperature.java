package com.timeforge.weatherservice.service.openweatherapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {
    private double min;
    private double max;
}
