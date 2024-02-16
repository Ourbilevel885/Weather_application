package com.api.wheatherproyect.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.wheatherproyect.dto.WheaterDataDTO;
import com.api.wheatherproyect.dto.forecast.ForecastDataDTO;
import com.api.wheatherproyect.dto.pollution.AirPollutionDTO;
import com.api.wheatherproyect.model.entity.City;
import com.api.wheatherproyect.model.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
	
	@Autowired
	private final CityRepository cityRepository;
	
	
	public void processCityWeather(WheaterDataDTO weatherDataDTO){
		
		Optional<City> existsCity = cityRepository.findByName(weatherDataDTO.getName());
		
		if (existsCity.isPresent()) {
			City city = existsCity.get();
			updateCityWeather(city, weatherDataDTO);
		}else {
			City city = new City();
			saveCityWeather(city, weatherDataDTO);
		}
	}
	
	
	private void saveCityWeather(City city, WheaterDataDTO weatherDataDTO) {

		city = City.builder()
						.name(weatherDataDTO.getName())
						.latitud(weatherDataDTO.getCoord().lat())
						.longitud(weatherDataDTO.getCoord().lat())
						.build();
		
		cityRepository.save(city);
		
	}

	private void updateCityWeather(City city, WheaterDataDTO weatherDataDTO) {
		
		
		if(weatherDataDTO.getCoord().lat() != null) {
			city.setLatitud(weatherDataDTO.getCoord().lat());
		}
		if (weatherDataDTO.getCoord().lon() != null) {
			city.setLongitud(weatherDataDTO.getCoord().lat());
		}
		
		cityRepository.save(city);
	}

	
	public void processCityForecast(ForecastDataDTO forecastDataDTO){
		
		Optional<City> existsCity = cityRepository.findByName(forecastDataDTO.getCity().getName());
		
		if (existsCity.isPresent()) {
			City city = existsCity.get();
			updateCityForecast(city, forecastDataDTO);
		}else {
			City city = new City();
			saveCityForecast(city, forecastDataDTO);
		}
	}
	
	
	
	
	private void saveCityForecast(City city, ForecastDataDTO forecastDataDTO) {
		city = City.builder()
				.name(forecastDataDTO.getCity().getName())
				.latitud(forecastDataDTO.getCity().getCoord().lat())
				.longitud(forecastDataDTO.getCity().getCoord().lat())
				.build();

		cityRepository.save(city);
		
	}


	private void updateCityForecast(City city, ForecastDataDTO forecastDataDTO) {
		
		if(forecastDataDTO.getCity().getCoord().lat() != null) {
			city.setLatitud(forecastDataDTO.getCity().getCoord().lat());
		}
		if (forecastDataDTO.getCity().getCoord().lon() != null) {
			city.setLongitud(forecastDataDTO.getCity().getCoord().lat());
		}
		
		cityRepository.save(city);
		
	}


	public void processCityPollution(AirPollutionDTO airPollutionDTO) {
		
		Optional<City> existsCity = cityRepository.findByLatitudAndLongitud(
																airPollutionDTO.getCoord().lat(), 
																airPollutionDTO.getCoord().lon());
		
		City city = existsCity.get();
		updateCityPollution(city, airPollutionDTO);
		
	}
	


	private void updateCityPollution(City city, AirPollutionDTO airPollutionDTO) {
		if(airPollutionDTO.getCoord().lat() != null) {
			city.setLatitud(airPollutionDTO.getCoord().lat());
		}
		if (airPollutionDTO.getCoord().lon() != null) {
			city.setLongitud(airPollutionDTO.getCoord().lat());
		}
		
		cityRepository.save(city);
		
	}


	public City obtenerCiudadPorNombre(String city) {
		return cityRepository.findByName(city).orElse(null);
	}


	


	
	
}
