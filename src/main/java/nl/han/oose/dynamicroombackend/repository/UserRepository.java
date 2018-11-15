package nl.han.oose.dynamicroombackend.repository;

import nl.han.oose.dynamicroombackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
