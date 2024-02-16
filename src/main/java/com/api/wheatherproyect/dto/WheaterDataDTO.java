package com.api.wheatherproyect.dto;

import com.api.wheatherproyect.dto.components.SysDTO;
import com.api.wheatherproyect.dto.components.WeatherDTO;
import com.api.wheatherproyect.dto.components.WeatherTimeMainDTO;
import com.api.wheatherproyect.dto.record.Clouds;
import com.api.wheatherproyect.dto.record.Coord;
import com.api.wheatherproyect.dto.record.Wind;

import java.util.List;

import lombok.*;

@Data
@RequiredArgsConstructor
public class WheaterDataDTO {
	
	private Coord coord;
	private List<WeatherDTO> weather;
	private String base;
	private WeatherTimeMainDTO main;
	private Integer visibility;
	private Wind wind;
	private Clouds clouds;
	private Long dt;
	private SysDTO sys;
	private Integer timezone;
	private Long id;
	private String name;
	private Integer cod;

}

