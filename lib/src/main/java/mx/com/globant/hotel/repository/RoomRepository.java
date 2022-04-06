package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.globant.hotel.entities.Room;

public interface RoomRepository extends JpaRepository<Room,Long>{

}
