package mx.com.globant.hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Guest;
import mx.com.globant.hotel.repository.GuestRepository;

@Service
public class GuestService {
	
	@Autowired 
	private GuestRepository guestRepository;
	
	public void create(Guest guest){
		if(guest ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else
			guestRepository.save(guest);		
	}
	
	public void delete(Guest guest) {
		guestRepository.delete(guest);
	}
	
	public List<Guest> getAll() {		
		return guestRepository.findAll();
	}
	
	public Guest getById(Long id) {
		Guest guest = guestRepository.getById(id);
		
		return guest;
	}	

}
