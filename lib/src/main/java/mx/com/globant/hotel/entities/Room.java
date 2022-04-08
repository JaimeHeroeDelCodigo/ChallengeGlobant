package mx.com.globant.hotel.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Embeddable
@SecondaryTable(name = "hotel", pkJoinColumns=@PrimaryKeyJoinColumn(name="id_hotel"))
@SecondaryTable(name = "room_type", pkJoinColumns=@PrimaryKeyJoinColumn(name="id_type"))
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;	
	@Column
	private String name;	
	@Column
	private String description;	
	@Column
	private short floor;	
	@Column
	private short max_guests;	
	
	@Embedded
	private RoomType type;	
	
	@Embedded
	private Hotel hotel;	
	
	
	
	
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
	public short getFloor() {
		return floor;
	}
	public void setFloor(short floor) {
		this.floor = floor;
	}
	public short getMax_guests() {
		return max_guests;
	}
	public void setMax_guests(short max_guests) {
		this.max_guests = max_guests;
	}
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
