package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.globant.hotel.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
