package co.com.udem.rea.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Estate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String estateCode;
	private float area;
	private int numOfRooms;
	private int numOfBathrooms;
	private boolean isForSale;
	private float estateValue;
	
	@ManyToOne
	private User user;
	
	public Estate(Long id, String estateCode, float area, int numOfRooms, int numOfBathrooms, boolean isForSale,
			float estateValue, User user) {
		super();
		this.id = id;
		this.estateCode = estateCode;
		this.area = area;
		this.numOfRooms = numOfRooms;
		this.numOfBathrooms = numOfBathrooms;
		this.isForSale = isForSale;
		this.estateValue = estateValue;
		this.user = user;
	}

	public Estate() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
