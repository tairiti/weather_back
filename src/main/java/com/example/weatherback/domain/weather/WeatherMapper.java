package com.example.weatherback.domain.weather;

import com.example.weatherback.business.weather.WeatherResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherMapper {

    @Mapping(source = "main.temp", target = "temperature")
    @Mapping(source = "wind.speed", target = "windSpeed")
    @Mapping(source = "main.humidity", target = "humidity")
    @Mapping(source = "timestamp", target = "time")
    Weather toWeather(WeatherApiResponse weatherApiResponse);



//    @Mapping(source = "time", target = "time")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "windSpeed", target = "windSpeed")
    @Mapping(source = "humidity", target = "humidity")
    WeatherResponse toWeatherResponse(Weather weather);
}