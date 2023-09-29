package com.example.weatherback.domain.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("select c from City c where c.name = ?1")
    City findCityBy(String name);


}