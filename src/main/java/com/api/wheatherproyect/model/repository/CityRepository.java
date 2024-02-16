package com.api.wheatherproyect.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.wheatherproyect.model.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
	Optional<City> findByName(String name);
	Optional<City> findByLatitudAndLongitud(Double latitud, Double longitud);
 }
