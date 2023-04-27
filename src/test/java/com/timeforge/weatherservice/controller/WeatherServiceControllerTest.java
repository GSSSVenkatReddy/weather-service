package com.timeforge.weatherservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.timeforge.weatherservice.businesslogic.IWeatherServiceBusinessLogic;
import com.timeforge.weatherservice.dto.WeatherServiceResponse;

@WebMvcTest(WeatherServiceController.class)
public class WeatherServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IWeatherServiceBusinessLogic businessLogic;

    private WeatherServiceResponse weatherServiceResponse;

    @BeforeEach
    void setUp() { 
        weatherServiceResponse = WeatherServiceResponse.builder()
                        .weeklyWeather(null)
                        .build();
    }
      
    @Test
    @DisplayName("getWeatherDetails GET call")
    void testGetWeatherDetails() throws Exception {
  
        Mockito.when(businessLogic.getWeatherDetails(79415, "openweather")).thenReturn(weatherServiceResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/getWeatherDetails?zipCode=79415&weatherApi=openweather")
        .contentType(MediaType.APPLICATION_JSON)
        .content(weatherServiceResponse.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
