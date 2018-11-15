package nl.han.oose.dynamicroombackend.repository;

import nl.han.oose.dynamicroombackend.model.ReservationParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationParticipantsRepository extends JpaRepository<ReservationParticipants, Long> {

}
