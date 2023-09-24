package com.example.weatherback.domain;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CityService {
@Resource
private CityRepository cityRepository;

    public void saveCity(City city) {
        cityRepository.save(city);
    }
    public City findCityBy(String cityName) {
        City city = cityRepository.findCityBy(cityName);
        return city;
    }
}
