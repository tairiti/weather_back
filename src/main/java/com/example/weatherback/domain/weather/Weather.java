package com.example.weatherback.domain.weather;

import com.example.weatherback.domain.city.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @NotNull
    @Column(name = "\"time\"", nullable = false)
    private Instant time;

    @NotNull
    @Column(name = "temperature", nullable = false)
    private Integer temperature;

    @NotNull
    @Column(name = "wind_speed", nullable = false, precision = 5, scale = 2)
    private BigDecimal windSpeed;

    @NotNull
    @Column(name = "humidity", nullable = false)
    private Integer humidity;

}