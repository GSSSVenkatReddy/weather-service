package com.timeforge.weatherservice.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;
import com.timeforge.weatherservice.dto.WeeklyWeather;
import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.Daily;
import com.timeforge.weatherservice.service.openweatherapi.response.OpenWeatherApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.Weather;

@Component
public class WeatherServiceTransformer {

    @Value("${weeklyweather.dateformat}")
    private String dateFormat;

    public WeatherServiceResponse transformToWeatherServiceResponseFromOpenWeatherApiResponse(OpenWeatherApiResponse openWeatherApiResponse) {
        WeatherServiceResponse weatherServiceResponse = new WeatherServiceResponse();
        List<WeeklyWeather> weeklyWeatherList = new ArrayList<>();
        for(int i=0; i < openWeatherApiResponse.getDaily().size() - 1; i++){
            Daily daily = openWeatherApiResponse.getDaily().get(i);
            WeeklyWeather weeklyWeather = new WeeklyWeather();
            weeklyWeather.setDate(new SimpleDateFormat(dateFormat).format(new Date (daily.getDt()*1000)));
            weeklyWeather.setLowTemperatute(daily.getTemp().getMin());
            weeklyWeather.setHighTemperature(daily.getTemp().getMax());
            weeklyWeather.setHumidity(daily.getHumidity());
            for(Weather weather : daily.getWeather()){
                weeklyWeather.setDescriptiveCondition(weather.getDescription());
            }
            weeklyWeather.setPrecipitationPercentage(daily.getPop() * 100.00);

            weeklyWeatherList.add(weeklyWeather);
        }
        weatherServiceResponse.setWeeklyWeather(weeklyWeatherList);    
        return weatherServiceResponse;
    }

    public String getLatitude(GeoCodingApiResponse geoCodingApiResponse) {
        String latitude = String.valueOf(geoCodingApiResponse.getPlus_code().getGeometry().getLocation().getLat());
        return latitude;
    }

    public String getLongitude(GeoCodingApiResponse geoCodingApiResponse) {
        String longitude = String.valueOf(geoCodingApiResponse.getPlus_code().getGeometry().getLocation().getLng());
        return longitude;
    }
    
}
