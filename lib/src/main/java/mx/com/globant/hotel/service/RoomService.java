package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Room;
import mx.com.globant.hotel.exception.RoomAlreadyExistsException;
import mx.com.globant.hotel.entities.Hotel;
import mx.com.globant.hotel.repository.RoomRepository;

@Service
public class RoomService {	
	@Autowired
	private RoomRepository roomRepository;	
	@Autowired
	private HotelService hotelService;
	
	public Room create(Room room) throws RoomAlreadyExistsException{
		if(room ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else {			
			Long idHotel = room.getIdHotel();
			Hotel hotel = hotelService
					        .getById(idHotel)
					        .orElseThrow(
					        		()->
					        		     new NullPointerException("El hotel con id " 
					        		                              + idHotel + "no existe"));
			Set<Room> rooms = hotel.getRooms();			
			if (!rooms.contains(room)){
				rooms.add(room);
				hotel.setRooms(rooms);
				hotelService.update(hotel);
				return roomRepository.save(room);				
			}else 
				throw new RoomAlreadyExistsException();						
		}
	}	
	
	public void deleteById(Long id) {
		roomRepository.deleteById(id);
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
