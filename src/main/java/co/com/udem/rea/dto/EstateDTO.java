package co.com.udem.rea.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class EstateDTO {

	private Long id;
	private String estateCode;
	private float area;
	private int numOfRooms;
	private int numOfBathrooms;
	private boolean isForSale;
	private float estateValue;
	
	@Autowired
	private UserDTO userDTO;
	
	public EstateDTO(Long id, String estateCode, float area, int numOfRooms, int numOfBathrooms, boolean isForSale,
			float estateValue, UserDTO userDTO) {
		super();
		this.id = id;
		this.estateCode = estateCode;
		this.area = area;
		this.numOfRooms = numOfRooms;
		this.numOfBathrooms = numOfBathrooms;
		this.isForSale = isForSale;
		this.estateValue = estateValue;
		this.userDTO = userDTO;
	}

	public EstateDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstateCode() {
		return estateCode;
	}

	public void setEstateCode(String estateCode) {
		this.estateCode = estateCode;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public int getNumOfBathrooms() {
		return numOfBathrooms;
	}

	public void setNumOfBathrooms(int numOfBathrooms) {
		this.numOfBathrooms = numOfBathrooms;
	}

	public boolean isForSale() {
		return isForSale;
	}

	public void setForSale(boolean isForSale) {
		this.isForSale = isForSale;
	}

	public float getEstateValue() {
		return estateValue;
	}

	public void setEstateValue(float estateValue) {
		this.estateValue = estateValue;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
}
