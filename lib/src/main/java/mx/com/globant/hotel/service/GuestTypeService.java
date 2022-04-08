package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.globant.hotel.entities.Guest;
import mx.com.globant.hotel.entities.GuestType;
import mx.com.globant.hotel.repository.GuestTypeRepository;

@Service
public class GuestTypeService {
	
	@Autowired
    private GuestTypeRepository guestTypeRepository;
	
	public void create(GuestType guestType){
		if(guestType ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else
			guestTypeRepository.save(guestType);		
	}
	
	public void delete(GuestType GuestType) {
		guestTypeRepository.delete(GuestType);
	}
	
	public List<GuestType> getAll() {		
		return guestTypeRepository.findAll();
	}
	
	public Optional<GuestType> getById(Long id) {
		Optional<GuestType> guestType = guestTypeRepository.findById(id);		
		return guestType;
	}
	
	public GuestType update(GuestType guestType) {
		return guestTypeRepository.save(guestType);
	}

}
