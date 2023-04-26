package com.timeforge.weatherservice.service.geocodingapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodingApiResponse {
    private PlusCode plus_code;
}
