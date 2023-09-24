package com.example.weatherback.business;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherInfoController {
    @Resource
    private WeatherInfoService weatherInfoService;

    @GetMapping("/weather")
    public void getWeatherInfo(@RequestParam String cityName) {
        weatherInfoService.getWeatherInfo(cityName);
    }
}
