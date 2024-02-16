package com.api.wheatherproyect.dto.components;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SysDTO {
		
	private Integer type;
	private Long id;
	private String country;
	private Long sunrise;
	private Long sunset;
}
