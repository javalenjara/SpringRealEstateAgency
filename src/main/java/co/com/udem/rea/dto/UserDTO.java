package co.com.udem.rea.dto;

import co.com.udem.rea.entity.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private IdType idType;
	private Long idNumber;
	private String address;
	private String phoneNumber;
	private String email;
	private String username;
	private String password;
	
}
