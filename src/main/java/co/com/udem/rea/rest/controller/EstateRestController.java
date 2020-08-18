package co.com.udem.rea.rest.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.rea.dto.EstateDTO;
import co.com.udem.rea.entity.Estate;
import co.com.udem.rea.entity.User;
import co.com.udem.rea.repository.EstateRepository;
import co.com.udem.rea.repository.UserRepository;
import co.com.udem.rea.security.jwt.JwtTokenProvider;
import co.com.udem.rea.util.Constants;
import co.com.udem.rea.util.EstateParser;
import net.minidev.json.JSONObject;

@RestController
public class EstateRestController {
	
	private EstateRepository estateRepository;
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private EstateParser convertEstate;
	
	@PostMapping(path="/estates/addEstate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addEstate(@RequestHeader("Authorization") String token, @RequestBody EstateDTO estateDTO) {
		String cleanToken =  token.contains("Bearer ") ? token.replace("Bearer ", "") : token;
		String username = jwtTokenProvider.getUsername(cleanToken);
		User user = userRepository.findByUsername(username).get();
		System.out.println(":::::USER:::::\n" + user.toString());
			
		JSONObject response = new JSONObject();
		try {
			estateDTO.setEstateCode(estateDTO.hashCode());//To-Do: validate if generates a good code or better use UUID.
			Estate estate = convertEstate.convertToEntity(estateDTO);
			estate.setUser(user);
			estateRepository.save(estate);
			response.put(Constants.HTTP_CODE, "200");
			response.put(Constants.SUCCESS_MESSAGE, "Estate added successfuly");
			return ResponseEntity.ok(response);
		} catch (ParseException e) {
			response.put(Constants.HTTP_CODE, "500");
			response.put(Constants.ERROR_MESSAGE, "An error ocurred while parsing the actual state DTO");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body(response);
		}
	}

}
