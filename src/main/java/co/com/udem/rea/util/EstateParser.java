package co.com.udem.rea.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.rea.dto.EstateDTO;
import co.com.udem.rea.entity.Estate;

public class EstateParser {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Estate convertToEntity(EstateDTO estateDTO) throws ParseException {
		return modelMapper.map(estateDTO, Estate.class);
	}
	
	public EstateDTO convertToDTO(Estate estate) throws ParseException {
		return modelMapper.map(estate, EstateDTO.class);
	}
}
