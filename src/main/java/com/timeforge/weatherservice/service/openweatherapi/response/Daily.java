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
public class Daily {
    private Long dt;
    private Temperature temp;
    private int humidity;
    private List<Weather> weather;
    private double pop;
}
