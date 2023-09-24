package com.example.weatherback.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;



public interface WeatherRepository extends JpaRepository<Weather, Integer> {



}