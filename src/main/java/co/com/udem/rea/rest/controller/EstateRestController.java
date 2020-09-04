package co.com.udem.rea.rest.controller;

import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.rea.dto.EstateDTO;
import co.com.udem.rea.entity.Estate;
import co.com.udem.rea.entity.User;
import co.com.udem.rea.exception.ResourceNotFoundException;
import co.com.udem.rea.repository.EstateRepository;
import co.com.udem.rea.repository.UserRepository;
import co.com.udem.rea.security.jwt.JwtTokenProvider;
import co.com.udem.rea.util.Constants;
import co.com.udem.rea.util.EstateParser;
import net.minidev.json.JSONObject;

@RestController
public class EstateRestController {
	
	@Autowired
	private EstateRepository estateRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EstateParser convertEstate;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping(path="/estates/addEstate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addEstate(@RequestHeader String token, @RequestBody EstateDTO estateDTO) throws ResourceNotFoundException {
		
		String username = jwtTokenProvider.getUsername(token);
		
		Optional<User> optionalUser = userRepository.findByUsername(username);
		User user;
		
		if(optionalUser.isPresent()) {
    		user = optionalUser.get();
		}
		else {
    		throw new ResourceNotFoundException("User not found with username: " + username);
    	}
		
		JSONObject response = new JSONObject();
			
		try {
			Estate estate = convertEstate.convertToEntity(estateDTO);
			estate.setUser(user);
			estate.setEstateCode(UUID.randomUUID());
			estateRepository.save(estate);
			response.put(Constants.HTTP_CODE, "200");
			response.put(Constants.SUCCESS_MESSAGE, "Estate added successfuly");
			return ResponseEntity.ok(response);
		} catch (ParseException e) {
			response.put(Constants.HTTP_CODE, "500");
			response.put(Constants.ERROR_MESSAGE, "An error ocurred while parsing the actual estate DTO");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body(response);
		}
	}
	
	@GetMapping("/estates")
    public Iterable<Estate> listEstates() {

    	return estateRepository.findAll();
    }
}
