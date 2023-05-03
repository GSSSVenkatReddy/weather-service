package com.timeforge.weatherservice.service;

import java.util.List;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;
import com.timeforge.weatherservice.entity.WeatherDetails;
import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.OpenWeatherApiResponse;
import com.timeforge.weatherservice.service.visualcrossingapi.response.VisualCrossingApiResponse;

public interface IWeatherService {

    public GeoCodingApiResponse geoCodingApiResponse(Integer zipCode);

    public OpenWeatherApiResponse openWeatherApiResponse(String latitude, String longitude);

    public void saveWeatherDetails(WeatherServiceResponse weatherServiceResponse, int zipCode, String weatherApi);

    public VisualCrossingApiResponse visualCrossingApiResponse(Integer zipCode);

    public List<WeatherDetails> getWeatherDetailsByZipCode(Integer zipCode, String weatherApi);
    
}
