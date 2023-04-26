package com.timeforge.weatherservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeatherDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sNo;
    private int zipCode;
    private String date;
    private double highTemperature;
    private double lowTemperature;
    private double humidity;
    private double precipitationPercentage;
    private String descriptiveCondition;
}
