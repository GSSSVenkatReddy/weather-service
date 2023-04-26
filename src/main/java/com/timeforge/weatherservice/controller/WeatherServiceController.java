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

    @GetMapping("/api/getWeatherDetails")
    public WeatherServiceResponse getWeatherDetails(@RequestParam(required = true) Integer zipCode, @RequestParam(required = true) String weatherApi) {
        return businessLogic.getWeatherDetails(zipCode, weatherApi);
    }
}
