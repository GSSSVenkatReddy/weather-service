package com.timeforge.weatherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.timeforge.weatherservice.entity.WeatherDetails;

@Component
public interface IWeatherServiceRepository extends JpaRepository<WeatherDetails, Integer> {
    
}
