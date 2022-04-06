package mx.com.globant.hotel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.globant.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long>{
	
}
