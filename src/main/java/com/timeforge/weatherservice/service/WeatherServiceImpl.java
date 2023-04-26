package com.timeforge.weatherservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;
import com.timeforge.weatherservice.dto.WeeklyWeather;
import com.timeforge.weatherservice.entity.WeatherDetails;
import com.timeforge.weatherservice.repository.IWeatherServiceRepository;
import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.OpenWeatherApiResponse;
import com.timeforge.weatherservice.service.visualcrossingapi.response.VisualCrossingApiResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherServiceImpl implements IWeatherService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IWeatherServiceRepository repository;

    @Value("${geocoding.apikey}")
    private String geoCodingApiKey;

    @Value("${geocoding.url}")
    private String geoCodingUrl;

    @Value("${openweather.apikey}")
    private String openWeatherApiKey;

    @Value("${openweather.url}")
    private String openWeatherUrl;

    @Value("${visualcrossing.url}")
    private String visualCrossingUrl;

    @Value("${visualcrossing.apikey}")
    private String visualCrossingApiKey;


    @Override
    public GeoCodingApiResponse geoCodingApiResponse(Integer zipCode, String weatherApi) {
        GeoCodingApiResponse geoCodingApiResponse = new GeoCodingApiResponse();
        try {
            geoCodingApiResponse = restTemplate.getForObject(geoCodingUrl, GeoCodingApiResponse.class, zipCode, geoCodingApiKey);
            if(null != geoCodingApiResponse){
                log.info(geoCodingApiResponse.toString());
            }
    
        } catch(Exception e) {
            log.error("unable to call geocodingapi service", e);
        }
        return geoCodingApiResponse;
    }

    @Override
    public OpenWeatherApiResponse openWeatherApiResponse(String latitude, String longitude) {
        OpenWeatherApiResponse openWeatherApiResponse = new OpenWeatherApiResponse();
        try {
            openWeatherApiResponse = restTemplate.getForObject(openWeatherUrl, OpenWeatherApiResponse.class, latitude, longitude, openWeatherApiKey);
            if(null != openWeatherApiResponse){
                log.info(openWeatherApiResponse.toString());
            }
        } catch(Exception e) {
            log.error("unable to call openweatherapi service", e);
        }
        return openWeatherApiResponse;
    }

    @Override
    public void saveWeatherDetails(WeatherServiceResponse weatherServiceResponse, int zipCode) {
        if(null != weatherServiceResponse.getWeeklyWeather()){
            for(WeeklyWeather weeklyWeather : weatherServiceResponse.getWeeklyWeather()){
                WeatherDetails weatherDetails = new WeatherDetails();
                weatherDetails.setZipCode(zipCode);
                weatherDetails.setDate(weeklyWeather.getDate());
                weatherDetails.setDescriptiveCondition(weeklyWeather.getDescriptiveCondition());
                weatherDetails.setHighTemperature(weeklyWeather.getHighTemperature());
                weatherDetails.setHumidity(weeklyWeather.getHumidity());
                weatherDetails.setLowTemperature(weeklyWeather.getLowTemperatute());
                weatherDetails.setPrecipitationPercentage(weeklyWeather.getPrecipitationPercentage());
    
                repository.save(weatherDetails);
            }
        }   
    }

    @Override
    public VisualCrossingApiResponse visualCrossingApiResponse(Integer zipCode) {
        VisualCrossingApiResponse visualCrossingApiResponse = new VisualCrossingApiResponse();
        try {
            visualCrossingApiResponse = restTemplate.getForObject(visualCrossingUrl, VisualCrossingApiResponse.class, zipCode, visualCrossingApiKey);
            if(null != visualCrossingApiResponse){
                log.info(visualCrossingApiResponse.toString());
            }
        } catch(Exception e) {
            log.error("unable to call visualcrossingapi service", e);
        }
        return visualCrossingApiResponse;
    }
    
}
