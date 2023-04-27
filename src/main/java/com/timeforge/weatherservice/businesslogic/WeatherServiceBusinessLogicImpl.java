package com.timeforge.weatherservice.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;
import com.timeforge.weatherservice.service.IWeatherService;
import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.OpenWeatherApiResponse;
import com.timeforge.weatherservice.service.visualcrossingapi.response.VisualCrossingApiResponse;
import com.timeforge.weatherservice.transformer.WeatherServiceTransformer;

@Component
public class WeatherServiceBusinessLogicImpl implements IWeatherServiceBusinessLogic{

    @Autowired
    private IWeatherService weatherService;

    @Autowired
    private WeatherServiceTransformer transformer;

    @Value("${weeklyweather.dateformat}")
    private String dateFormat;

    @Value("${api.openweather}")
    private String openWeatherApi;

    @Value("${api.visualcrossing}")
    private String visualcrossingApi;

    /**
    * Returns an object that contains weather details for a week to the controller
    * Contains specific business logic for which weather api to consume 
    *  
    * @param  zipCode  zipcode of a location
    * @param  weatherApi type of weather api to be used for getting weather details
    * @return  WeatherServiceResponse 
    */
    @Override
    public WeatherServiceResponse getWeatherDetails(Integer zipCode, String weatherApi) {

        WeatherServiceResponse weatherServiceResponse = new WeatherServiceResponse();

        if(weatherApi.equals(openWeatherApi)){
            GeoCodingApiResponse geoCodingApiResponse =  weatherService.geoCodingApiResponse(zipCode);

            OpenWeatherApiResponse openWeatherApiResponse = weatherService.openWeatherApiResponse(transformer.getLatitude(geoCodingApiResponse), transformer.getLongitude(geoCodingApiResponse));
    
            weatherServiceResponse = transformer.transformToWeatherServiceResponseFromOpenWeatherApiResponse(openWeatherApiResponse);
        }
        
        if(weatherApi.equals(visualcrossingApi)){
            VisualCrossingApiResponse visualCrossingApiResponse = weatherService.visualCrossingApiResponse(zipCode);

            weatherServiceResponse = transformer.transformToWeatherServiceResponseFromVisualCrossingApiResponse(visualCrossingApiResponse);
        }
        
        weatherService.saveWeatherDetails(weatherServiceResponse, zipCode);

        return weatherServiceResponse;
    }
    
}
