package pl.akademiakodu.miniblog.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiakodu.miniblog.model.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUserName(String userName);
}
