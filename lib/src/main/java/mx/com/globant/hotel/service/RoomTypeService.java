package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.RoomType;
import mx.com.globant.hotel.repository.RoomTypeRepository;


@Service
public class RoomTypeService {
	@Autowired
    private RoomTypeRepository roomTypeRepository;
	
	public RoomType create(RoomType roomType){
		if(roomType ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else 
			return roomTypeRepository.save(roomType);	
	}
	
	public void deleteById(Long id) {
		roomTypeRepository.deleteById(id);  ;
	}
	
	public List<RoomType> getAll() {		
		return roomTypeRepository.findAll();
	}
	
	public Optional<RoomType> getById(Long id) {
		Optional<RoomType> roomType = roomTypeRepository.findById(id);		
		return roomType;
	}
	
	public RoomType update(RoomType roomType) {
		return roomTypeRepository.save(roomType);
	}


}
