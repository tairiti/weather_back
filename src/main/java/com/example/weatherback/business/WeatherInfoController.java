package com.example.weatherback.business;

import com.example.weatherback.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherInfoController {
    @Resource
    private WeatherInfoService weatherInfoService;

    @GetMapping("/weather")
    @Operation(summary = "Linna nime järgi ilmainfo toomine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Sellise nimega linna ei ole andmebaasis",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public WeatherResponse getWeatherInfo(@RequestParam String cityName) {
        return weatherInfoService.getWeatherInfo(cityName);
    }

    @DeleteMapping("/weather")
    public void deleteCityAndWeatherInfo(@RequestParam String cityName) {
        weatherInfoService.deleteCityAndWeatherInfo(cityName);
    }
}
