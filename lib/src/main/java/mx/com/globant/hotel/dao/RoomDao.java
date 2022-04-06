package mx.com.globant.hotel.dao;

public class RoomDao {
	
	private Long id;
	private String name;
	private String description;
	private short floor;
	private short max_guests;
	private RoomType type;
	private HotelDao hotel;
	
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
	public HotelDao getHotel() {
		return hotel;
	}
	public void setHotel(HotelDao hotel) {
		this.hotel = hotel;
	}

}
