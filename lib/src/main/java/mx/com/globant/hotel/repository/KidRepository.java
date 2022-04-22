package mx.com.globant.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.globant.hotel.entities.Kid;

@Repository
public interface KidRepository extends JpaRepository<Kid,Long>{
}
