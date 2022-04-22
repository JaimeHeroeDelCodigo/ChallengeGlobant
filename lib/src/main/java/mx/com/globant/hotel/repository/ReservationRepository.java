package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.globant.hotel.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
