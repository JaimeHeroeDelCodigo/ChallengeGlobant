package mx.com.globant.hotel.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name="hotel")
public class Hotel {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Long hotel_id;	
	@Column(name="name")
	private String name;	
	@Column(name="description")
	private String description;	
	@Column(name="stars")
	private short stars;	
	
	@OneToMany(mappedBy="hotel")
    private Set<Room> rooms;	
	
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setId(Long hotel_id) {
		this.hotel_id = hotel_id;
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
