package com.timeforge.weatherservice.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.timeforge.weatherservice.dto.WeatherServiceResponse;
import com.timeforge.weatherservice.dto.WeeklyWeather;
import com.timeforge.weatherservice.entity.WeatherDetails;
import com.timeforge.weatherservice.service.geocodingapi.response.GeoCodingApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.Daily;
import com.timeforge.weatherservice.service.openweatherapi.response.OpenWeatherApiResponse;
import com.timeforge.weatherservice.service.openweatherapi.response.Weather;
import com.timeforge.weatherservice.service.visualcrossingapi.response.Days;
import com.timeforge.weatherservice.service.visualcrossingapi.response.VisualCrossingApiResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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

    public WeatherServiceResponse transformToWeatherServiceResponseFromVisualCrossingApiResponse(
            VisualCrossingApiResponse visualCrossingApiResponse) {
        WeatherServiceResponse weatherServiceResponse = new WeatherServiceResponse();
        List<WeeklyWeather> weeklyWeatherList = new ArrayList<>();
        for(int i=0; i < visualCrossingApiResponse.getDays().size() - 8; i++){
            Days days = visualCrossingApiResponse.getDays().get(i);
            WeeklyWeather weeklyWeather = new WeeklyWeather();
            weeklyWeather.setDate(days.getDatetime());
            weeklyWeather.setLowTemperatute(days.getTempmin());
            weeklyWeather.setHighTemperature(days.getTempmax());
            weeklyWeather.setHumidity(days.getHumidity());
            weeklyWeather.setDescriptiveCondition(days.getDescription());
            weeklyWeather.setPrecipitationPercentage(days.getPrecipprob());

            weeklyWeatherList.add(weeklyWeather);
        }
        weatherServiceResponse.setWeeklyWeather(weeklyWeatherList);
        return weatherServiceResponse;
    }

    public WeatherServiceResponse transformEntityResponseToWeatherServiceResponse(
            List<WeatherDetails> weatherDetailsList) {
        WeatherServiceResponse weatherServiceResponse = new WeatherServiceResponse();
        List<WeeklyWeather> weeklyWeatherList = new ArrayList<>();
        // traverse through the weatherDetailsList and prepare the WeatherServiceResponse
        for(WeatherDetails weatherDetails : weatherDetailsList){
            WeeklyWeather weeklyWeather = new WeeklyWeather();
            weeklyWeather.setDate(weatherDetails.getDate());
            weeklyWeather.setDescriptiveCondition(weatherDetails.getDescriptiveCondition());
            weeklyWeather.setHighTemperature(weatherDetails.getHighTemperature());
            weeklyWeather.setHumidity(weeklyWeather.getHumidity());
            weeklyWeather.setLowTemperatute(weeklyWeather.getLowTemperatute());
            weeklyWeather.setPrecipitationPercentage(weeklyWeather.getPrecipitationPercentage());

            weeklyWeatherList.add(weeklyWeather);
        }
        weatherServiceResponse.setWeeklyWeather(weeklyWeatherList);
        return weatherServiceResponse;
    }

    public Long findTimeDifferenceInMinutes(List<WeatherDetails> weatherDetailsList) {
        long difference_In_Minutes = 16;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if(!weatherDetailsList.isEmpty()){
            String apiCallDate = weatherDetailsList.get(0).getDateTimeOfApiCall();
            String currentDate = sdf.format(new Date());
            try {
                Date d1 = sdf.parse(currentDate);
                Date d2 = sdf.parse(apiCallDate);
                long difference_In_Time = d1.getTime() - d2.getTime();
                difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
                log.info("difference in minutes "+difference_In_Minutes);
            } catch(Exception e) {
                log.error("Error in findTimeDifferenceInMinutes() method" + e);
            }

        }
        return difference_In_Minutes;
    }
    
}
