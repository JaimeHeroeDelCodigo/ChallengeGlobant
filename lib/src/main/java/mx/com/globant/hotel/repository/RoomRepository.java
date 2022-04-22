package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.globant.hotel.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{

}
