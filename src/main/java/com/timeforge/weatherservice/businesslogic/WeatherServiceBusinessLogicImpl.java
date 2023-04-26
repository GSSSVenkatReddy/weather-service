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

    @Override
    public WeatherServiceResponse getWeatherDetails(Integer zipCode, String weatherApi) {

        WeatherServiceResponse weatherServiceResponse = new WeatherServiceResponse();

        if(weatherApi.equals(openWeatherApi)){
            GeoCodingApiResponse geoCodingApiResponse =  weatherService.geoCodingApiResponse(zipCode, weatherApi);

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
