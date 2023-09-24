package com.example.weatherback.business;

import com.example.weatherback.domain.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@Service
public class WeatherInfoService {
    @Resource
    private WeatherService weatherService;
    @Resource
    private WeatherMapper weatherMapper;
    @Resource
    private CityService cityService;
    private final String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String apiKey = "aea7ac7652aad657fd91bec36c74b6e0";
    private final WebClient webClient;
    private String cityName;

    @Autowired
    public WeatherInfoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(weatherApiUrl).build();
    }
    public void getWeatherInfo(String cityName) {
        this.cityName = cityName;
        WeatherApiResponse weatherApiResponse = getWeatherApiInfo();
        saveCity(cityName);
        City city = cityService.findCityBy(cityName);
        saveWeatherApiInfo(weatherApiResponse, city);
    }
    private void saveCity(String cityName) {
        City city = new City();
        city.setName(cityName);
        cityService.saveCity(city);
    }
    private void saveWeatherApiInfo(WeatherApiResponse weatherApiResponse, City city) {
        Weather weather = weatherMapper.toWeather(weatherApiResponse);
        weather.setCity(city);
        weatherService.saveWeatherInfo(weather);
    }
//    @Scheduled(cron = "*/15 * * * *")
    public WeatherApiResponse getWeatherApiInfo() {
        String apiUrl = weatherApiUrl + cityName + "&appid=" + apiKey + "&units=metric";

        WeatherApiResponse weatherApiResponse = webClient
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(WeatherApiResponse.class).block();
        weatherApiResponse.setTimestamp(Instant.now());
        return weatherApiResponse;
    }
}
