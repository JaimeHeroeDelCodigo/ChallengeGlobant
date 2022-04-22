package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.globant.hotel.entities.ReservationsBinnacle;

@Repository
public interface ReservationsBinnacleRepository extends JpaRepository<ReservationsBinnacle,Long>{

}
