package com.timeforge.weatherservice.service;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;
import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.OpenWeatherApiResponse;

public interface IWeatherService {

    public GeoCodingApiResponse geoCodingApiResponse(Integer zipCode, String weatherApi);

    public OpenWeatherApiResponse openWeatherApiResponse(String latitude, String longitude);

    public void saveWeatherDetails(WeatherServiceResponse weatherServiceResponse, int zipCode);
    
}
