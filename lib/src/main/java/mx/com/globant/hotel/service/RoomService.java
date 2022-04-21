package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Room;
import mx.com.globant.hotel.exception.GuestLimitExceededException;
import mx.com.globant.hotel.exception.RoomAlreadyExistsException;
import mx.com.globant.hotel.exception.RoomInReservation;
import mx.com.globant.hotel.entities.Hotel;
import mx.com.globant.hotel.entities.Reservation;
import mx.com.globant.hotel.repository.ReservationRepository;
import mx.com.globant.hotel.repository.RoomRepository;
import java.util.ArrayList;

@Service
public class RoomService {	
	@Autowired
	private RoomRepository roomRepository;	
	@Autowired
	private HotelService hotelService;	
	@Autowired 
	private ReservationRepository reservationRepository;
	
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
	
	public void deleteById(Long id) throws RoomInReservation {		
		Long idHotel = roomRepository
				         .findById(id)
				         .orElseThrow(
				             ()-> new NullPointerException("El cuarto con id " + id + "no existe"))
				                       .getIdHotel();	
		ArrayList<Long> ids = new ArrayList<Long>();
		reservationRepository
		            .findAll()
		            .stream()
		            .map(Reservation::getRooms)
		            .forEach(s -> extractIds(ids,s));
		if( ids.contains(idHotel))
			throw new RoomInReservation();
		else
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
	private void extractIds( ArrayList<Long> lista,Set<Room> r){
		r.forEach(  c -> 
		              lista.add( c.getId()));		
	}	
	public void addKids(Short k,Room room) throws GuestLimitExceededException{
		if (k>room.getMax_guests())
			throw new GuestLimitExceededException();
		else {
			Short actKids = room.getNoKids();
		   	room.setNoKids((short) (actKids + k));			
		}	   	
	}
}
