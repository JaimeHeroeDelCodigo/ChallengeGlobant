package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;

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
		else {
			
			
			
			
			guestRepository.save(guest);
		}
					
	}
	
	public void deleteById(Long id_hotel) {
		guestRepository.deleteById(id_hotel);		
	}
	
	public List<Guest> getAll() {		
		return guestRepository.findAll();
	}
	
	public Optional<Guest> getById(Long id) {
		Optional<Guest> guest = guestRepository.findById(id);		
		return guest;
	}
	
	public Guest update(Guest guest) {
		return guestRepository.save(guest);
	}
	
	

}
