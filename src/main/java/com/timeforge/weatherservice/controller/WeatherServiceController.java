package com.timeforge.weatherservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timeforge.weatherservice.businesslogic.IWeatherServiceBusinessLogic;
import com.timeforge.weatherservice.dto.WeatherServiceResponse;

@RestController
public class WeatherServiceController {

    @Autowired
    private IWeatherServiceBusinessLogic businessLogic;

    /**
    * Returns an object that contains weather details for a week to the calling application 
    *
    * @param  zipCode  zipcode of a location
    * @param  weatherApi type of weather api to be used for getting weather details
    * @return  WeatherServiceResponse 
    */
    @GetMapping("/api/getWeatherDetails")
    public WeatherServiceResponse getWeatherDetails(@RequestParam(required = true) Integer zipCode, @RequestParam(required = true) String weatherApi) {
        return businessLogic.getWeatherDetails(zipCode, weatherApi);
    }
}
