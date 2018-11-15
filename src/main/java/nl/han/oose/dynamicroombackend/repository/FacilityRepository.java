package nl.han.oose.dynamicroombackend.repository;

import nl.han.oose.dynamicroombackend.model.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends CrudRepository<Facility, String> {
}
