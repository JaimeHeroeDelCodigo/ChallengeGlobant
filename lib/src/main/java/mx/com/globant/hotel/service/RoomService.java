package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.globant.hotel.entities.Room;
import mx.com.globant.hotel.repository.RoomRepository;


@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;    
	
	public void create(Room room){
		if(room ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else
			roomRepository.save(room);		
	}
	
	public void delete(Room Room) {
		roomRepository.delete(Room);
	}
	
	public List<Room> getAll() {		
		return roomRepository.findAll();
	}
	
	public Optional<Room> getById(Long id) {
		Optional<Room> room = roomRepository.findById(id);		
		return room;
	}
	
	public Room update(Room room) {
		return roomRepository.save(room);
	}

}
