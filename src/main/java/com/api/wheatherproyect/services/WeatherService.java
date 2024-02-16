package com.api.wheatherproyect.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.api.wheatherproyect.dto.WheaterDataDTO;
import com.api.wheatherproyect.dto.forecast.ForecastDataDTO;
import com.api.wheatherproyect.dto.pollution.AirPollutionDTO;
import com.api.wheatherproyect.dto.WeatherResponse;
import com.api.wheatherproyect.model.entity.City;
import com.api.wheatherproyect.model.entity.Queries;
import com.api.wheatherproyect.model.repository.QueriesRepository;
import com.api.wheatherproyect.security.model.entity.Usuario;
import com.api.wheatherproyect.security.services.UsuarioService;


@Service
public class WeatherService {
		private static final String URL_API = "https://api.openweathermap.org/data/2.5";
		private static final String KEY_API = "74f7c03aaf8671579df02212f3f8ceeb";
		
		private final RestTemplate restTemplate;

		@Autowired
		private CityService cityService;
		
		@Autowired
		private UsuarioService usuarioService;
		
		@Autowired
		private QueriesRepository queriesRepository;
		
		public WeatherService(RestTemplateBuilder restTemplateBuilder) {
	        this.restTemplate = restTemplateBuilder.build();
	    }
		/**
		 * 
		 * 
		 * 
		 * Wheater current get and save
		 * 
		 * 
		 * 
		 * */
		public ResponseEntity<?> weatherCurrent(String city, String username){
			WeatherResponse weatherResponse = new WeatherResponse();
			
	        try {
	        	ResponseEntity<WheaterDataDTO> response = this.restTemplate.getForEntity(this.urlCurrent(city), WheaterDataDTO.class);
	        	WheaterDataDTO weatherDataDTO = response.getBody();
	        	
	        	cityService.processCityWeather(weatherDataDTO);
	        	
	        	saveWeatherCurrent(city, username, weatherDataDTO);
	        	
	        	weatherResponse = WeatherResponse.builder()
	        									.city(city)
	        									.date(LocalDate.now())
	        									.latitud(weatherDataDTO.getCoord().lat())
	        									.longitud(weatherDataDTO.getCoord().lon())
	        									.temperature(weatherDataDTO.getMain().getTemp())
	        									.humidity(weatherDataDTO.getMain().getHumidity())
	        									.build();
	                
	        } catch (HttpClientErrorException e) {
	        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
		}
		
		private void saveWeatherCurrent(String city, String username, WheaterDataDTO weatherDataDTO) {
			
			City ciudad = cityService.obtenerCiudadPorNombre(city); 
			Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(username);
			
			Queries queries = Queries.builder()
										.date(LocalDate.now())
										.city(ciudad)
										.usuario(usuario)
										.tipo("Clima actual")
										.descripcion(weatherDataDTO.getWeather().get(0).getDescription())
										.build();
			
			queriesRepository.save(queries);
			
		}
		
		/**
		 * 
		 * 
		 * 
		 * Wheater forecast get and save
		 * 
		 * 
		 * 
		 * */

		public ResponseEntity<?> weatherForecast(String city, String username){
			ForecastDataDTO forecastDataDTO = new ForecastDataDTO();
			
	        try {
	        	ResponseEntity<ForecastDataDTO> response = this.restTemplate.getForEntity(this.urlForecast(city), ForecastDataDTO.class);
	        	forecastDataDTO = response.getBody();
	        	
	            cityService.processCityForecast(forecastDataDTO);
	            saveWeatherForecast(city, username, forecastDataDTO);
	            
	       
	        } catch (HttpClientErrorException e) {
	        	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }

	       // return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
	        return new ResponseEntity<>(forecastDataDTO, HttpStatus.OK);
		}
		
		private void saveWeatherForecast(String city, String username, ForecastDataDTO forecastDataDTO) {
			
			City ciudad = cityService.obtenerCiudadPorNombre(city); 
			Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(username);
			
			Queries queries = Queries.builder()
					.date(LocalDate.now().plusDays(5))
					.city(ciudad)
					.usuario(usuario)
					.tipo("Pronostico 5 dias")
					.descripcion(forecastDataDTO.getList().get(0).getWeather().get(0).getDescription())
					.build();
			
			queriesRepository.save(queries);
		}
		
		/**
		 * 
		 * 
		 * 
		 * air pollution get and save
		 * 
		 * 
		 * 
		 * */
		
		public ResponseEntity<?> airPollution(String city, String username){
			
			City ciudad = cityService.obtenerCiudadPorNombre(city);
			
			Double lon = ciudad.getLongitud();
			Double lat = ciudad.getLatitud();
			
			AirPollutionDTO airPollutionDTO = new AirPollutionDTO();
			
	        try {
	        	ResponseEntity<AirPollutionDTO> response = this.restTemplate.getForEntity(this.urlPollution(lat, lon), AirPollutionDTO.class);
	        	airPollutionDTO = response.getBody();
	        	
	            cityService.processCityPollution(airPollutionDTO);
	            saveWeatherPollution(city, username, airPollutionDTO);
	            
	       
	        } catch (HttpClientErrorException e) {
	        	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }

	       // return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
	        return new ResponseEntity<>(airPollutionDTO, HttpStatus.OK);
		}
		
		private void saveWeatherPollution(String city, String username, AirPollutionDTO airPollutionDTO) {
			
			City ciudad = cityService.obtenerCiudadPorNombre(city); 
			Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(username);
			
			Queries queries = Queries.builder()
					.date(LocalDate.now())
					.city(ciudad)
					.usuario(usuario)
					.tipo("Contaminacion del aire")
					.descripcion(airPollutionDTO.getList().get(0).getComponents().toString())
					.build();
			
			queriesRepository.save(queries);
		}

		
		public void saveCity(String city){
			ResponseEntity<WheaterDataDTO> response = this.restTemplate.getForEntity(this.urlCurrent(city), WheaterDataDTO.class);
        	WheaterDataDTO weatherDataDTO = response.getBody();
        	
        	cityService.processCityWeather(weatherDataDTO);

			
		}
		
		private String urlForecast(String city) {
	        return String.format(URL_API.concat("/forecast").concat("?q=%s").concat("&lang=es").concat("&appid=%s").concat("&units=metric").concat("&cnt=5"), city, KEY_API);
	    }
	    
		
	    private String urlCurrent(String city) {
	        return String.format(URL_API.concat("/weather").concat("?q=%s").concat("&appid=%s").concat("&lang=es"), city, KEY_API);
	    }
	    
	    private String urlPollution(Double lat, Double lon) {
	        return String.format(URL_API.concat("/air_pollution").concat("?lat=%s").concat("&lon=%s").concat("&appid=%s"), lat, lon, KEY_API);
	    }
		
}