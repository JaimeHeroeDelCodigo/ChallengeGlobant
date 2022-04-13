package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Hotel;
import mx.com.globant.hotel.entities.Room;
import mx.com.globant.hotel.exception.RoomAlreadyExistsException;
import mx.com.globant.hotel.repository.HotelRepository;
import mx.com.globant.hotel.repository.RoomRepository;
import static java.util.stream.Collectors.*;

@Service
public class HotelService{	
	@Autowired 
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	public Hotel create(Hotel hotel) throws RoomAlreadyExistsException{
		if(hotel ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else {
			Set<Long> roomsIds   =  hotel
					                  .getRooms()
					                  .stream()
					                  .map(Room::getId)
					                  .collect(toSet());
			
			Set<Long> roomRegIds =  roomRepository
					                  .findAll()
					                  .stream()
					                  .map(Room::getId)
					                  .collect(toSet());
			
			roomsIds.retainAll(roomRegIds);			
			if ( roomsIds.size()==0 ){
				
				System.out.println("LOS CUARTOS A GUARDAR SON:");
				System.out.println(hotel.getRooms());
				
				
				hotel.getRooms().forEach( roomRepository::save);				
				return hotelRepository.save(hotel);
			}else
				throw new RoomAlreadyExistsException("Los cuartos con id:\n " +			                                         
						                                 roomsIds.toString()  +						                                 
					 	                              "\nYa han sido registrados.");			
		}
			
	}
	
	public void deleteById(Long id_hotel) {
		hotelRepository.deleteById(id_hotel);		
	}
	
	public List<Hotel> getAll() {		
		return hotelRepository.findAll();
	}
	
	public Optional<Hotel> getById(Long id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);		
		return hotel;
	}	
	
	public Hotel update(Hotel hotel){
		return hotelRepository.save(hotel);
	}
}
