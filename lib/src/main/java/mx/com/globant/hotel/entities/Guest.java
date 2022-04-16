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
@Table(name ="guest")
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", table = "guest")
	private Long id;	
	@Column(name="first_name")
	private String first_name;	
	@Column(name="last_name")
	private String last_name;	
	@Column(name="email")
	private String email;
	@Column(name="vip")
	private Boolean vip;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="type_id",referencedColumnName="id")
	private GuestType guestType;
	
	@ManyToOne
	@JoinColumn(name="reservation_id", nullable=true)
	private Reservation reservation;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getVip() {
		return vip;
	}
	public void setVip(Boolean vip) {
		this.vip = vip;
	}	

}
