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
	
	
	
	
	
	
	
	
	
	
	
	
}
