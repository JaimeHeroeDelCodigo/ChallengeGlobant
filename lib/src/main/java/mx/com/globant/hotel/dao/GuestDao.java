package mx.com.globant.hotel.dao;

public class GuestDao {
	private Long id;
	private String first_name;
	private  String last_name;
	private String email;
	private TypeGuest type_guest;
	
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

}
