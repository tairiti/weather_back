package com.example.weatherback.business;

import com.example.weatherback.domain.city.City;
import com.example.weatherback.domain.city.CityService;
import com.example.weatherback.domain.weather.Weather;
import com.example.weatherback.domain.weather.WeatherApiResponse;
import com.example.weatherback.domain.weather.WeatherMapper;
import com.example.weatherback.domain.weather.WeatherService;
import com.example.weatherback.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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

    private final RestTemplate restTemplate;
    private String cityName;

    @Autowired
    public WeatherInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeatherInfo(String cityName) {
        this.cityName = cityName;
        try {
            scheduledFetchWeatherData();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new DataNotFoundException("Sellise nimega linna ei ole andmebaasis", 111);
        }
        return weatherService.getWeatherInfoBy(cityName);
    }
//    @Scheduled(cron = "* 15 * * * *")
    @Scheduled(cron = "* * * * * *")
    public void scheduledFetchWeatherData() {
        if(cityName != null){
        fetchWeatherData();
        }
    }
    public void fetchWeatherData() {
        String apiUrl = weatherApiUrl + cityName + "&appid=" + apiKey + "&units=metric";
        WeatherApiResponse weatherApiResponse = restTemplate.getForObject(apiUrl, WeatherApiResponse.class);

        City existingCity = cityService.findCityBy(cityName);
        if (existingCity == null) {
            saveCity(cityName);
        }
        weatherApiResponse.setTimestamp(Instant.now());
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

    public void deleteCityAndWeatherInfo(String cityName) {
        weatherService.deleteWeatherInfoBy(cityName);
        cityService.deleteCityBy(cityName);
    }
}
