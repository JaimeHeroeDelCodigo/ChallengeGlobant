package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Guest;
import mx.com.globant.hotel.entities.Reservation;
import mx.com.globant.hotel.entities.Room;
import mx.com.globant.hotel.exception.GuestAlreadyRegisteredException;
import mx.com.globant.hotel.exception.GuestAlreadyVIPException;
import mx.com.globant.hotel.exception.GuestLimitExceededException;
import mx.com.globant.hotel.exception.GuestNotAvailableVIPException;
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
	
	public Reservation create(Reservation reservation) throws NullPointerException, 
	                                                          RoomNotExistingException,
	                                                          GuestNotExistingException, 
	                                                          GuestLimitExceededException{			
		
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
			
			if(!roomsIds.containsAll(reservRoomsIds))
				throw new RoomNotExistingException();
			else if(!guestIds.containsAll(reservGuestIds))
				throw new GuestNotExistingException();
			else if (!verificaNumGuest(reservation).isEmpty())
				throw new GuestLimitExceededException();			
			else
				
				return reservationRepository.save(reservation);					
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
	
	private ArrayList<Long> verificaNumGuest(Reservation reservation) {
		ArrayList<Long> ids = new ArrayList<Long>();
		Set<Room> rooms = reservation.getRooms();		
		HashMap<Long,Short> guestPerRoom = new HashMap<Long,Short>();		
		HashMap<Long,Short> maxGuestRoom = new HashMap<Long,Short>();		
				
		//   Se crea un mapa donde guardaremos
		//   el número de invitados por habitación
		for(Iterator<Room> iter = rooms.iterator();iter.hasNext();) {
			Room room = iter.next();			
			guestPerRoom.put(room.getId(), (short) 0);
			maxGuestRoom.put(room.getId(),room.getMax_guests());
		}
		reservation
		   .getGuests()
		   .stream()
		   .forEach(g->
		              {guestPerRoom
		            	     .compute(g.getId(),(k,v)->
		            	                              v++);});		
		
	    for( Map.Entry<Long,Short> e :guestPerRoom.entrySet()) {
	    	Long idRoom = e.getKey();
	    	if(  e.getValue() >  maxGuestRoom.get(idRoom))
	    		ids.add(idRoom);
	    }		
		return ids;
		
	}
	
	// Metodo para agregar invitado a reservación	
	public Reservation addGuestToRoom(Guest guest,Long idReserv,Room room) throws GuestLimitExceededException,
	                                                                        GuestAlreadyRegisteredException,
	                                                                        GuestNotExistingException,
	                                                                        GuestAlreadyVIPException,
	                                                                        GuestNotAvailableVIPException {		
		Integer totalGuests = 0;
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.findAll();
		for (Iterator<Reservation> reserv = reservations.iterator();reserv.hasNext();) {
			totalGuests +=  (reserv.next().getReservation_id()==idReserv ) ? 1 : 0;
			if(totalGuests>= (room.getMax_guests() + room.getNoKids() ))
				throw new GuestLimitExceededException();			
		}		
		Reservation r = reservationRepository.getById(idReserv);	              
		Set<Room> rooms =  r.getRooms();		
		rooms.add(room);
		r.setRooms(rooms);	  	

	  	Set<Guest> guests = r.getGuests();
		if(guests.contains(guest))
			throw new GuestAlreadyRegisteredException();
	  	else
	  		guests.add(guest);
		    r.setGuests(guests);
		    vipMakeGuest(guest.getId(),true);
		    
	   return reservationRepository.save(r);		
	}	
	
	// Revisión de número de reservaciones de un cliente para poder hacerlo VIP
	// act false: no tomamos en cuenta la resevacion recien hecha, cont=0
	// act true: tomamos en cuenta la resevacion recien hecha, cont=1
	public Guest vipMakeGuest(Long idGuest,Boolean act) throws GuestNotExistingException,
	                                                GuestAlreadyVIPException, GuestNotAvailableVIPException {
		
		int cont=  act?1:0;
		Guest guest = guestRepository
				             .findById(idGuest)
				             .orElseThrow(() ->
				                       new GuestNotExistingException());
		
		if(guest.getVip())
			throw new GuestAlreadyVIPException();		
		ArrayList<Reservation> reservations =  (ArrayList<Reservation>) reservationRepository.findAll();
		
		for (Iterator<Reservation> reserv = reservations.iterator(); reserv.hasNext();) {
			if(reserv.next().getGuests().contains(guest))
				if(++cont==5) {
					guest.setVip(true);
					guestRepository.save(guest);
					break;
				}					
		}
		if(cont>=5)
			throw new GuestNotAvailableVIPException();		
		return guest;
	}
}
