package com.example.weatherback.domain.weather;
import com.example.weatherback.business.weather.WeatherResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WeatherService {
    @Resource
    private WeatherRepository weatherRepository;
    @Resource
    private WeatherMapper weatherMapper;

    public void saveWeatherInfo(Weather weather) {
        weatherRepository.save(weather);
    }

    public WeatherResponse getWeatherInfoBy(String cityName) {
        Weather weather = weatherRepository.getWeatherBy(cityName);
        return weatherMapper.toWeatherResponse(weather);
    }

    public void deleteWeatherInfoBy(String cityName) {
        List<Weather> weatherList = weatherRepository.getWeatherListBy(cityName);
        for (Weather weather : weatherList) {
            weatherRepository.delete(weather);
        }
    }
}
