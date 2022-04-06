package mx.com.globant.hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.Reservation;
import mx.com.globant.hotel.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public void create(Reservation reservation){
		if(reservation ==null)
			throw new NullPointerException("La entidad a guardar no puede ser null");
		else
			reservationRepository.save(reservation);		
	}
	
	public void delete(Reservation Reservation) {
		reservationRepository.delete(Reservation);
	}
	
	public List<Reservation> getAll() {		
		return reservationRepository.findAll();
	}
	
	public Reservation getById(Long id) {
		Reservation reservation = reservationRepository.getById(id);
		
		return reservation;
	}

}
