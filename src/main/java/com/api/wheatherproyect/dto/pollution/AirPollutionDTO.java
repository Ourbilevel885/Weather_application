package com.api.wheatherproyect.dto.pollution;

import java.util.List;

import com.api.wheatherproyect.dto.components.PollutionMapDTO;
import com.api.wheatherproyect.dto.record.Coord;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AirPollutionDTO {
	private Coord coord;
	private List<PollutionMapDTO> list;
	
}
