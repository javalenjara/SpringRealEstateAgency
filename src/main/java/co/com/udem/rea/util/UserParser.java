package co.com.udem.rea.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.rea.dto.UserDTO;
import co.com.udem.rea.entity.User;


public class UserParser {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User convertToEntity(UserDTO userDTO) throws ParseException {
		return modelMapper.map(userDTO, User.class);
	}
	
	public UserDTO convertToDTO(User user) throws ParseException {
		return modelMapper.map(user, UserDTO.class);
	}

}
