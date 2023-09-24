package com.example.weatherback.business.weather;

import com.example.weatherback.domain.weather.Weather;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link Weather}
 */
@Value
public class WeatherResponse implements Serializable {
//    @NotNull
//    Instant time;
    @NotNull
    Integer temperature;
    @NotNull
    BigDecimal windSpeed;
    @NotNull
    Integer humidity;
}