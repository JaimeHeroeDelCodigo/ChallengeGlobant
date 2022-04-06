package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.globant.hotel.entities.GuestType;

public interface GuestTypeRepository extends JpaRepository<GuestType,Long>{

}
