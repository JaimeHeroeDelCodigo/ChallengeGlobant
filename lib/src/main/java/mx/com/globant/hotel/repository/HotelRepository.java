package mx.com.globant.hotel.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.globant.hotel.dao.HotelDao;

public interface HotelRepository extends JpaRepository<HotelDao,Long>{
	
}
