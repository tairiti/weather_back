-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-09-23 12:39:26.728

-- tables
-- Table: city
CREATE TABLE city
(
    id   serial       NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT city_pk PRIMARY KEY (id)
);

-- Table: weather
CREATE TABLE weather
(
    id          serial        NOT NULL,
    city_id     int           NOT NULL,
    time        timestamp     NOT NULL,
    temperature int           NOT NULL,
    wind_speed  decimal(5, 2) NOT NULL,
    humidity    int           NOT NULL,
    CONSTRAINT weather_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: weather_city (table: weather)
ALTER TABLE weather
    ADD CONSTRAINT weather_city
        FOREIGN KEY (city_id)
            REFERENCES city (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- End of file.

