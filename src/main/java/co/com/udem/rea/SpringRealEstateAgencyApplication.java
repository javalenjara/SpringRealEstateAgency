package co.com.udem.rea;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.com.udem.rea.dto.UserDTO;
import co.com.udem.rea.util.EstateParser;
import co.com.udem.rea.util.UserParser;

@SpringBootApplication
//@EnableDiscoveryClient
public class SpringRealEstateAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRealEstateAgencyApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper( ) {
		return new ModelMapper();
	}
	
	@Bean
	public EstateParser estateParser() {
		return new EstateParser();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserParser userParser() {
		return new UserParser();
	}
	
	@Bean
	public UserDTO userDTO() {
		return new UserDTO();
	}

}
