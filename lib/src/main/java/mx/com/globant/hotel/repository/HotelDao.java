package mx.com.globant.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.globant.hotel.dao.Hotel;

public interface HotelDao extends JpaRepository<Hotel,Long>{
	
	
	// Búsqueda en la tabla por id
	Optional<Hotel> findById(Long id);
	
	// Obtener una lista con todos lo shoteles
	List<Hotel> findAll();
	
	// <S extends T> S saveAndFlush(S entity);
	Hotel saveAndFlush(Hotel hotel);
	
	
	void deleteAllInBatch(Iterable<Hotel> entities);
	
	
	
	

	
	
	
}
