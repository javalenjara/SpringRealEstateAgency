package co.com.udem.rea.rest.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.rea.dto.UserDTO;
import co.com.udem.rea.entity.User;
import co.com.udem.rea.repository.UserRepository;
import co.com.udem.rea.util.Constants;
import co.com.udem.rea.util.UserParser;

@RestController
public class UserRestController {
	
	@Autowired
    private UserRepository userRepository;
   
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    UserParser convertUser;
   
    @PostMapping("/users/addUser")
    public Map<String, String> adicionarUsuario(@RequestBody UserDTO userDTO) {
        Map<String, String> response = new HashMap<>();
        
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
			        .password(this.passwordEncoder.encode(user.getPassword()))
			        .roles(Arrays.asList("ROLE_USER"))
			        .build());
		} catch (ParseException e) {
			response.put(Constants.ERROR_MESSAGE, "Something went wrong while creating a new user.");
			return response;
		}
        response.put(Constants.HTTP_CODE, "200");
        response.put(Constants.SUCCESS_MESSAGE, "OK. New user added successfuly.");
        return response;
    }
   
    @GetMapping("/users")
    public Iterable<User> listarUsuarios() {

    	return userRepository.findAll();
    }
}
