package mx.com.globant.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.globant.hotel.entities.RoomType;
import mx.com.globant.hotel.repository.RoomTypeRepository;


@Service
public class RoomTypeService {
	@Autowired
    private RoomTypeRepository roomTypeRepository;
	
	public void create(RoomType roomType){
		if(roomType ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else
			roomTypeRepository.save(roomType);		
	}
	
	public void delete(RoomType RoomType) {
		roomTypeRepository.delete(RoomType);
	}
	
	public List<RoomType> getAll() {		
		return roomTypeRepository.findAll();
	}
	
	public RoomType getById(Long id) {
		RoomType roomType = roomTypeRepository.getById(id);
		
		return roomType;
	}


}
