package com.api.wheatherproyect.dto.components;

import java.math.BigDecimal;

import lombok.*;

@Data
@RequiredArgsConstructor
public class WeatherTimeMainDTO {
	
	private BigDecimal temp;
	private BigDecimal feels_like;
	private BigDecimal temp_min;
	private BigDecimal temp_max;
	private Integer pressure;
	private Integer humidity;
	
}
