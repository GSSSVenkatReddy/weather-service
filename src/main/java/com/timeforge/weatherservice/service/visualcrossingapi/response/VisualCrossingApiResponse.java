package com.timeforge.weatherservice.service.visualcrossingapi.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisualCrossingApiResponse {
    private List<Days> days;
}
