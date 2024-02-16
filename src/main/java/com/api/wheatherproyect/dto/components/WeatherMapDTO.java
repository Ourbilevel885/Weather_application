package com.api.wheatherproyect.dto.components;

import java.time.LocalDate;
import java.util.List;

import com.api.wheatherproyect.dto.record.Clouds;
import com.api.wheatherproyect.dto.record.Sys;
import com.api.wheatherproyect.dto.record.Wind;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WeatherMapDTO {
	private Long dt;
	private WeatherTimeMainDTO main;
	private List<WeatherDTO> weather;
	private Clouds clouds;
	private Wind wind;
	private Integer visibility;
	private Double pop;
	private Sys sys;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dt_txt")
	private String dt_txt;
	
}
