package com.timeforge.weatherservice.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;
import com.timeforge.weatherservice.service.IWeatherService;
import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.OpenWeatherApiResponse;
import com.timeforge.weatherservice.transformer.WeatherServiceTransformer;

@Component
public class WeatherServiceBusinessLogicImpl implements IWeatherServiceBusinessLogic{

    @Autowired
    private IWeatherService weatherService;

    @Autowired
    private WeatherServiceTransformer transformer;

    @Value("${weeklyweather.dateformat}")
    private String dateFormat;

    @Override
    public WeatherServiceResponse getWeatherDetails(Integer zipCode, String weatherApi) {
        
        GeoCodingApiResponse geoCodingApiResponse =  weatherService.geoCodingApiResponse(zipCode, weatherApi);

        OpenWeatherApiResponse openWeatherApiResponse = weatherService.openWeatherApiResponse(transformer.getLatitude(geoCodingApiResponse), transformer.getLongitude(geoCodingApiResponse));

        WeatherServiceResponse weatherServiceResponse = transformer.transformToWeatherServiceResponseFromOpenWeatherApiResponse(openWeatherApiResponse);

        weatherService.saveWeatherDetails(weatherServiceResponse, zipCode);

        return weatherServiceResponse;
    }
    
}
