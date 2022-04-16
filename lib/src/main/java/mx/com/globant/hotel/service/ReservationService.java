package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Guest;
import mx.com.globant.hotel.entities.Reservation;
import mx.com.globant.hotel.entities.Room;
import mx.com.globant.hotel.exception.GuestNotExistingException;
import mx.com.globant.hotel.exception.RoomNotExistingException;
import mx.com.globant.hotel.repository.GuestRepository;
import mx.com.globant.hotel.repository.ReservationRepository;
import mx.com.globant.hotel.repository.RoomRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;	
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private GuestRepository guestRepository;	
	
	public void create(Reservation reservation) throws RoomNotExistingException, GuestNotExistingException{
		if(reservation ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else {			
			Set<Long> roomsIds = roomRepository
					                .findAll()
					                .stream()
					                .map(Room::getId)
					                .collect(toSet());			
			Set<Long> reservRoomsIds = reservation
					                .getRooms()
					                .stream()
					                .map(Room::getId)
					                .collect(toSet());			
			Set<Long> guestIds = guestRepository
					                .findAll()
					                .stream()
					                .map(Guest::getId)
					                .collect(toSet());			
			Set<Long> reservGuestIds = reservation
					                 .getGuests()
					                 .stream()
					                 .map(Guest::getId)
					                 .collect(toSet());
        
			if(roomsIds.containsAll(reservRoomsIds))
				if( guestIds.containsAll(reservGuestIds)) {					
					reservationRepository.save(reservation);
				}else 
					throw new GuestNotExistingException();				
			else 
				throw new RoomNotExistingException();		
		}		
		
	}	
	
		
	public void deleteById(Long id) {
		reservationRepository.deleteById(id);;
	}
	
	public List<Reservation> getAll() {		
		return reservationRepository.findAll();
	}
	
	public Optional<Reservation> getById(Long id) {
		Optional<Reservation> reservation = reservationRepository.findById(id);		
		return reservation;
	}
	
	public Reservation update(Reservation reservation){
		return reservationRepository.save(reservation);
	}

}
