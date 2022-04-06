package mx.com.globant.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public GuestType getById(Long id) {
		GuestType guestType = guestTypeRepository.getById(id);
		
		return guestType;
	}

}
