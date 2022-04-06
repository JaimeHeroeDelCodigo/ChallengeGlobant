package mx.com.globant.hotel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="guest")
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String first_name;
	
	@Column
	private String last_name;
	
	@Column
	private String email;
	
	@Column
	private GuestType type_guest;	
	
	
	/*
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
	public TypeGuest getType_guest() {
		return type_guest;
	}
	public void setType_guest(TypeGuest type_guest) {
		this.type_guest = type_guest;
	}
*/
}
