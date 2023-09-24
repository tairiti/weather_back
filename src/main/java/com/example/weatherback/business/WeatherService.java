package com.example.weatherback.business;
import com.example.weatherback.domain.Weather;
import com.example.weatherback.domain.WeatherRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {
    @Resource
    private WeatherRepository weatherRepository;

    public void saveWeatherInfo(Weather weather) {
        weatherRepository.save(weather);
    }
}
