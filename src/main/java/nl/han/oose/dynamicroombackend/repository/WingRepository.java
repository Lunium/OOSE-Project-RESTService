package nl.han.oose.dynamicroombackend.repository;

import nl.han.oose.dynamicroombackend.model.Wing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WingRepository extends CrudRepository<Wing, String>{
}
