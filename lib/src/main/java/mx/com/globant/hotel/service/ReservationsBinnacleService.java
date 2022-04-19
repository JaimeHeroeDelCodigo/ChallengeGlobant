package mx.com.globant.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.globant.hotel.entities.ReservationsBinnacle;
import mx.com.globant.hotel.repository.ReservationsBinnacleRepository;

@Service
public class ReservationsBinnacleService {
	
	
	@Autowired
	private ReservationsBinnacleRepository reservationsBinnacleRepository;	
	
	public ReservationsBinnacle create(ReservationsBinnacle reservation) {	
		return reservationsBinnacleRepository.save(reservation);
	}
	
	public ReservationsBinnacle cancel(ReservationsBinnacle reservation) {		
		reservation.setCancel(true);
		return reservationsBinnacleRepository.save(reservation);		
	}	

}
