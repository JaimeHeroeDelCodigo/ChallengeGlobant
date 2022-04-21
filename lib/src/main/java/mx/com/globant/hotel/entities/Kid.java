package mx.com.globant.hotel.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kid")
public class Kid implements Serializable{
	private static final long serialVersionUID = 3597929310127206693L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private Long id_kid;
	@Column(name="id_guest")
	private Long id_guest;
	@Column(name="id_reservation")
    private Long id_reservation;
	
	
	public Long getId_kid() {
		return id_kid;
	}
	public void setId_kid(Long id_kid) {
		this.id_kid = id_kid;
	}
	public Long getId_guest() {
		return id_guest;
	}
	public void setId_guest(Long id_guest) {
		this.id_guest = id_guest;
	}
	public Long getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(Long id_reservation) {
		this.id_reservation = id_reservation;
	}	
}
