package mx.com.globant.hotel.entities;

import java.sql.Date;

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
@SecondaryTable(name = "guest", pkJoinColumns=@PrimaryKeyJoinColumn(name="guest_id"))
@SecondaryTable(name = "room", pkJoinColumns=@PrimaryKeyJoinColumn(name="room_id")) 
@Table(name = "reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;	
	@Column(name="start_date")
	private Date start_date;	
	@Column(name="end_date")
	private Date end_date;	
	@Column(name="check_in")
	private Boolean check_in;	
	@Column(name="check_out")
	private Boolean check_out;
	
	@Embedded
	private Guest guest;
	
	@Embedded
	private Room room;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
}
