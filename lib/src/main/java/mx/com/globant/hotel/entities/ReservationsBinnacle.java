package mx.com.globant.hotel.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ReservationsBinnacle")
public class ReservationsBinnacle {	
	
	@Id
	@Column(name ="id")
	private Long id;	
	
	@Column(name="hotel_name")
	private String hotel_name;
	
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="guest_first_name")
	private String guest_first_name;
	
	@Column(name="guest_last_name")
	private String guest_last_name;
	
	@Column(name="guest_email")
	private String guest_email;
	
	@Column(name="start_date")
	private Date start_date;
	
	@Column(name="end_date")
	private Date end_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getGuest_first_name() {
		return guest_first_name;
	}

	public void setGuest_first_name(String guest_first_name) {
		this.guest_first_name = guest_first_name;
	}

	public String getGuest_last_name() {
		return guest_last_name;
	}

	public void setGuest_last_name(String guest_last_name) {
		this.guest_last_name = guest_last_name;
	}

	public String getGuest_email() {
		return guest_email;
	}

	public void setGuest_email(String guest_email) {
		this.guest_email = guest_email;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}
