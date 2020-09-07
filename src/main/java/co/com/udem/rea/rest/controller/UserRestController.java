package co.com.udem.rea.rest.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.rea.dto.UserDTO;
import co.com.udem.rea.entity.User;
import co.com.udem.rea.exception.ResourceNotFoundException;
import co.com.udem.rea.repository.UserRepository;
import co.com.udem.rea.util.Constants;
import co.com.udem.rea.util.UserParser;
import net.minidev.json.JSONObject;

@RestController
public class UserRestController {
	
	@Autowired
    private UserRepository userRepository;
   
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserParser convertUser;
      
    @PostMapping(path="/users/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addUser(@RequestBody UserDTO userDTO) {

		JSONObject response = new JSONObject();
		try {
			User user = convertUser.convertToEntity(userDTO);
			userRepository.save(User.builder()
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.idType(user.getIdType())
					.idNumber(user.getIdNumber())
					.address(user.getAddress())
					.phoneNumber(user.getPhoneNumber())
					.email(user.getEmail())
					.username(user.getUsername())
			        .password(this.passwordEncoder.encode(user.getPassword()))
			        .roles(Arrays.asList("ROLE_USER"))
			        .build());
			response.put(Constants.HTTP_CODE, "200");
			response.put(Constants.SUCCESS_MESSAGE, "User added successfuly");
			return ResponseEntity.ok(response);
		} catch (ParseException e) {
			response.put(Constants.HTTP_CODE, "500");
			response.put(Constants.ERROR_MESSAGE, "An error ocurred while parsing the actual user DTO");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body(response);
		}
	}
   
    @GetMapping("/users")
    public Iterable<User> listUsers() {

    	return userRepository.findAll();
    }
    
    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> listUser(@PathVariable Long userId) throws ResourceNotFoundException {
    	
    	JSONObject response = new JSONObject();
    	
    	Optional<User> optionalUser = userRepository.findById(userId);
    	if(optionalUser.isPresent()) {
    		response.put(Constants.HTTP_CODE, "200");
    		response.put(Constants.SUCCESS_MESSAGE, "User finded");
    		response.put("User", optionalUser.get());
    		return ResponseEntity.ok(response);
    	}else {
    		throw new ResourceNotFoundException("User not found with id " + userId);
    	}
    }
    
    @PutMapping(path = "/users/update/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserInfo(@PathVariable Long userId, @RequestBody UserDTO updatedUserDTO) throws ResourceNotFoundException {
    	
    	JSONObject response = new JSONObject();
    	User newUser;
    	Optional<User> optionalUser = userRepository.findById(userId);
    	if(optionalUser.isPresent()) {
    		newUser = optionalUser.get();
    	}else {
    		throw new ResourceNotFoundException("User not found with id " + userId);
    	}
    	
    	newUser.setFirstName(updatedUserDTO.getFirstName());
    	newUser.setLastName(updatedUserDTO.getLastName());
    	newUser.setIdType(updatedUserDTO.getIdType());
    	newUser.setIdNumber(updatedUserDTO.getIdNumber());
    	newUser.setAddress(updatedUserDTO.getAddress());
    	newUser.setPhoneNumber(updatedUserDTO.getAddress());
    	newUser.setEmail(updatedUserDTO.getEmail());
    	
    	userRepository.save(newUser);
    	
    	response.put(Constants.HTTP_CODE, "200");
		response.put(Constants.SUCCESS_MESSAGE, "User modified successfuly");
		return ResponseEntity.ok(response);
    }
    
    @PutMapping(path = "/users/pwd/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserPassword(@PathVariable Long userId, @RequestBody String newPassword) throws ResourceNotFoundException {
    	
    	JSONObject response = new JSONObject();
    	Optional<User> optionalUser = userRepository.findById(userId);
    	
    	if(optionalUser.isPresent()) {
    		User newUser = optionalUser.get();
    		newUser.setPassword(this.passwordEncoder.encode(newPassword));
    		userRepository.save(newUser);
    		response.put(Constants.HTTP_CODE, "200");
    		response.put(Constants.SUCCESS_MESSAGE, "Password modified successfuly");
    		return ResponseEntity.ok(response);
    	}else {
    		throw new ResourceNotFoundException("User not found with id " + userId);
    	}
    }
    
    @DeleteMapping(path = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) throws ResourceNotFoundException {
        
    	JSONObject response = new JSONObject();
    	Optional<User> optionalUser = userRepository.findById(userId);
    	
    	if(optionalUser.isPresent()) {
    		userRepository.deleteById(userId);
    		response.put(Constants.HTTP_CODE, "200");
    		response.put(Constants.SUCCESS_MESSAGE, "User id " + userId + " deleted.");
    		return ResponseEntity.ok(response);
    	}else {
    		throw new ResourceNotFoundException("User not found with id " + userId);
    	}
    }
}
