package co.com.udem.rea.dto.parser;

import java.text.ParseException;

import org.modelmapper.ModelMapper;

import co.com.udem.rea.dto.IdTypeDTO;
import co.com.udem.rea.entity.IdType;

public class IdTypeParser {
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public IdType convertToEntity(IdTypeDTO idTypeDTO) throws ParseException {
		return modelMapper.map(idTypeDTO, IdType.class);
	}
}
