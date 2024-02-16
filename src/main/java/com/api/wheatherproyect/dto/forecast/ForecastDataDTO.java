package com.api.wheatherproyect.dto.forecast;

import java.util.List;

import com.api.wheatherproyect.dto.components.CityDTO;
import com.api.wheatherproyect.dto.components.WeatherMapDTO;

import lombok.*;

@Data
@RequiredArgsConstructor
public class ForecastDataDTO {
	private Integer cod;
	private Integer message;
	private Integer cnt;
	private List<WeatherMapDTO> list;
	private CityDTO city;
}
