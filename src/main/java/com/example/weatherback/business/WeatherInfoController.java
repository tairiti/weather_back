package com.example.weatherback.business;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherInfoController {
    @Resource
    private WeatherInfoService weatherInfoService;

    @GetMapping("/weather")
    public WeatherResponse getWeatherInfo(@RequestParam String cityName) {
        return weatherInfoService.getWeatherInfo(cityName);
    }

    @DeleteMapping("/weather")
    public void deleteCityAndWeatherInfo(@RequestParam String cityName) {
        weatherInfoService.deleteCityAndWeatherInfo(cityName);
    }
}
