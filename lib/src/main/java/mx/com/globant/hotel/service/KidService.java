package mx.com.globant.hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Kid;
import mx.com.globant.hotel.repository.KidRepository;

@Service
public class KidService {	
	@Autowired
	private KidRepository kidRepository;
	
	public Kid create(Kid kid) {		
		return kidRepository.save(kid);		
	}	
	public void deleteById(Long id) {
		kidRepository.deleteById(id);
	}
	public List<Kid> getAll(){
		return kidRepository.findAll();		
	}
	
    // Solo se puede cambiar el id del guest
	public Kid update(Kid kidAct) {
		Kid kid = kidRepository
				   .findById(kidAct.getId_kid())
				   .orElseThrow(() ->
				                     new NullPointerException("El id del niño no existe"));		
		kid.setId_guest( kidAct.getId_guest() );		
		return kidRepository.save(kid);
	}
}
