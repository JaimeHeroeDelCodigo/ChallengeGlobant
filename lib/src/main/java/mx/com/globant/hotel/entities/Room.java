package mx.com.globant.hotel.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Long room_id;	
	@Column
	private String name;	
	@Column
	private String description;	
	@Column
	private short floor;	
	@Column
	private short max_guests;	
	@Column 
	private Long idHotel;
	 
	
	@ManyToOne
	@JoinColumn(name="reservation_id", nullable=true)
	private Reservation reservation;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_type", referencedColumnName = "id")
	private RoomType roomType;
	
	@ManyToOne
    @JoinColumn(name="hotel_id", nullable=true) 
	private Hotel hotel;	
	
	public Long getId() {
		return room_id;
	}
	public void setId(Long room_id) {
		this.room_id = room_id;
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
		return roomType;
	}
	public void setType(RoomType type) {
		this.roomType = type;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Long getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}	
	
	@Override
	public String toString() {
		return "Room [room_id=" + room_id + ", name=" + name + ", description=" + description + ", floor=" + floor
				+ ", max_guests=" + max_guests + ", idHotel=" + idHotel + ", reservation=" + reservation + ", roomType="
				+ roomType + ", hotel=" + hotel + "]";
	}	
}
