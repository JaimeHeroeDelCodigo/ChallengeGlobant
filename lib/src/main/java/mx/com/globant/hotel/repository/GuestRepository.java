package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.globant.hotel.entities.Guest;


public interface GuestRepository extends JpaRepository<Guest,Long>{

}
