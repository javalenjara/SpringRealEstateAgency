package co.com.udem.rea.dto;

import co.com.udem.rea.entity.IdType;

public class UserDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private IdType idType;
	private Long idNumber;
	private String address;
	private String phoneNumber;
	private String email;
	private String password;
	
	public UserDTO(Long id, String firstName, String lastName, IdType idType, Long idNumber, String address,
			String phoneNumber, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idType = idType;
		this.idNumber = idNumber;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

	public UserDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}
	
}
