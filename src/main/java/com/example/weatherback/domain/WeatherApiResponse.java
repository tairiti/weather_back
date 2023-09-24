package com.example.weatherback.domain;

import lombok.Data;
import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Weather}
 */

@Data
public class WeatherApiResponse implements Serializable {

        private Main main;
        private Wind wind;

        @Data
        public static class Main {
            private Double temp;
            private Integer humidity;
        }

        @Data
        public static class Wind {
            private Double speed;
        }

    private Instant timestamp;

}