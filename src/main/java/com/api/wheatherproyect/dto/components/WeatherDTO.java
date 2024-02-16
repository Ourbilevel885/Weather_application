package com.api.wheatherproyect.dto.components;

import lombok.*;


@Data
@RequiredArgsConstructor
public class WeatherDTO {
	
	private Long id;
	private String main;
	private String description;
}
