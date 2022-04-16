package mx.com.globant.hotel.entities;

import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long reservation_id;	
	@Column(name="start_date")
	private Date start_date;	
	@Column(name="end_date")
	private Date end_date;	
	@Column(name="check_in")
	private Boolean check_in;	
	@Column(name="check_out")
	private Boolean check_out;	
	
	@OneToMany(mappedBy="reservation")
	private Set<Guest> guests;
	 
	@OneToMany(mappedBy="reservation")
	private Set<Room> rooms;

	public Long getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
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

	public Boolean getCheck_in() {
		return check_in;
	}

	public void setCheck_in(Boolean check_in) {
		this.check_in = check_in;
	}

	public Boolean getCheck_out() {
		return check_out;
	}

	public void setCheck_out(Boolean check_out) {
		this.check_out = check_out;
	}

	public Set<Guest> getGuests() {
		return guests;
	}

	public void setGuests(Set<Guest> guests) {
		this.guests = guests;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}	
}
