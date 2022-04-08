package mx.com.globant.hotel.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Embeddable
//@Table(name="guest_type")
public class GuestType {	
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id",table = "guest_type")
	private Long id;
	
	
	@Column(name="name")
	private String name;	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
