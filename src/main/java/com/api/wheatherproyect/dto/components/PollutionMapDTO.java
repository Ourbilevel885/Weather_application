package com.api.wheatherproyect.dto.components;

import java.util.Map;

import com.api.wheatherproyect.dto.record.Main;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PollutionMapDTO {
	private Main main;
	private Map<String, Double> components;
	private Long dt;
}
