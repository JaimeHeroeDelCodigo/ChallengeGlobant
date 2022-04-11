package mx.com.globant.hotel.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@Table(name="hotel")
public class Hotel implements Serializable {
	
	private static final long serialVersionUID = 3399092165316194915L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
    @JsonIgnore
	private Long hotel_id;	
	@Column(name="name")
	private String name;	
	@Column(name="description")
	private String description;	
	@Column(name="stars")
	private short stars;	
	
	@OneToMany(mappedBy="hotel")
    private Set<Room> rooms;	
	
	
	
	
	
	
	public Hotel() {
		super();		
	}	
	
	public Hotel(String name, String description, short stars) {
		super();
		this.name = name;
		this.description = description;
		this.stars = stars;
	}
	
	
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
	@Override
	public String toString() {
		return "Hotel [hotel_id=" + hotel_id + ", name=" + name + ", description=" + description + ", stars=" + stars
				+ ", rooms=" + rooms + "]";
	}
	
	




}
