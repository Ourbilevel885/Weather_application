package com.api.wheatherproyect.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
	
	private LocalDate date;
	private String city;
	private Double latitud;
	private Double longitud;
	private BigDecimal temperature;
	private Integer humidity;

	
}
