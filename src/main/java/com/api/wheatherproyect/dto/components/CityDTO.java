package com.api.wheatherproyect.dto.components;

import com.api.wheatherproyect.dto.record.Coord;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CityDTO {
	
	private Long id;
	private String name;
	private Coord coord;
	private String country;
	private Long population;
	private Integer timezone;
	private Long sunrise;
	private Long sunset;
}
