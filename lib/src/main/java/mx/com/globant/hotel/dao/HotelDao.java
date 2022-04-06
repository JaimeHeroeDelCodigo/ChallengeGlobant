package mx.com.globant.hotel.dao;

public class HotelDao {
	
	private Long id;
	private String name;
	private String description;
	private short stars;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public short getStars() {
		return stars;
	}
	public void setStars(short stars) {
		this.stars = stars;
	}
}
