package com.timeforge.weatherservice.businesslogic;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;

public interface IWeatherServiceBusinessLogic {

    public WeatherServiceResponse getWeatherDetails(Integer zipCode, String weatherApi);
    
}
