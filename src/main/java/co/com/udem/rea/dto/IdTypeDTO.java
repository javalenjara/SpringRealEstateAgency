package co.com.udem.rea.dto;

public class IdTypeDTO {
	
	private Long id;
	private String type;
	private String description;
	
	public IdTypeDTO(Long id, String type, String description) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
	}

	public IdTypeDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
