package com.timeforge.weatherservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.visualcrossingapi.response.VisualCrossingApiResponse;

@SpringBootTest
public class IWeatherServiceTest {

    @Autowired
    @MockBean
    private IWeatherService weatherService;

    @BeforeEach
    void setUp() {
        GeoCodingApiResponse geoCodingApiResponse = GeoCodingApiResponse
                            .builder()
                            .status("OK")
                            .build();
        Mockito.when(weatherService.geoCodingApiResponse(530028)).thenReturn(geoCodingApiResponse);

        VisualCrossingApiResponse visualCrossingApiResponse = VisualCrossingApiResponse
                            .builder()
                            .address("79415")
                            .build();
        Mockito.when(weatherService.visualCrossingApiResponse(79415)).thenReturn(visualCrossingApiResponse);
    }

    @Test
    @DisplayName("Mock GeoCoding API call")
    public void whenValidZipCodeThenCheckStatusOfGeocoding() {
        GeoCodingApiResponse geoCodingApiResponse = weatherService.geoCodingApiResponse(530028);
        assertEquals("OK", geoCodingApiResponse.getStatus());
    }

    @Test
    @DisplayName("VisualCrossing API call")
    public void validatingZipCodeFromVisualCrossingAPiResponse() {
        VisualCrossingApiResponse visualCrossingApiResponse = weatherService.visualCrossingApiResponse(79415);
        assertEquals(79415, Integer.parseInt(visualCrossingApiResponse.getAddress()));
    }

}
