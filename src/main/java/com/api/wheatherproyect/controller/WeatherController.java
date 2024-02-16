package com.api.wheatherproyect.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.wheatherproyect.model.entity.City;
import com.api.wheatherproyect.model.repository.CityRepository;
import com.api.wheatherproyect.services.CityService;
import com.api.wheatherproyect.services.WeatherService;



@RestController
@RequestMapping("weather")
public class WeatherController {
	
	@Autowired
    private WeatherService weatherService;
	
	@Autowired
	private CityRepository cityRepository;
	
	@GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherCurrent(@RequestParam(required = true) String city) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
        return weatherService.weatherCurrent(city, username);
    }
	
	@GetMapping(value = "/forecast", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherForecast(@RequestParam(required = true) String city) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
        return weatherService.weatherForecast(city, username);
    }
	
	@GetMapping(value = "/air_pollution", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherPollution(@RequestParam(required = true) String city) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		City cities = cityRepository.findByName(city).orElse(null);
		
		if(cities == null) {
			weatherService.saveCity(city);
		}
		
        return weatherService.airPollution(city, username);
    }
}
