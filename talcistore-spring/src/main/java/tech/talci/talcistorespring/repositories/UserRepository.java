package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
