package com.example.weatherback.domain;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherMapper {

    @Mapping(source = "main.temp", target = "temperature")
    @Mapping(source = "wind.speed", target = "windSpeed")
    @Mapping(source = "main.humidity", target = "humidity")
    @Mapping(source = "timestamp", target = "time")
    Weather toWeather(WeatherApiResponse weatherApiResponse);

}