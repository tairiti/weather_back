package com.example.weatherback.domain.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    @Query("select w from Weather w where w.city.name = ?1")
    Weather getWeatherBy(String name);

    @Query("select w from Weather w where w.city.name = ?1")
    List<Weather> getWeatherListBy(String name);


}