package mx.com.globant.hotel.service;

import java.util.List;
import java.util.Optional;

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
