package mx.com.globant.hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.globant.hotel.entities.Hotel;
import mx.com.globant.hotel.repository.HotelRepository;

@Service
public class HotelService{
	
	@Autowired 
	private HotelRepository hotelRepository;
	
	public void create(Hotel hotel){
		if(hotel ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else
			hotelRepository.save(hotel);		
	}
	
	public void delete(Hotel hotel) {
		hotelRepository.delete(hotel);
	}
	
	public List<Hotel> getAll() {		
		return hotelRepository.findAll();
	}
	
	public Hotel getById(Long id) {
		Hotel hotel = hotelRepository.getById(id);
		
		return hotel;
	}	
}
