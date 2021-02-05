package tech.talci.talcistorespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcistorespring.model.CustomerDetails;
import tech.talci.talcistorespring.model.User;

import java.util.Optional;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    Optional<CustomerDetails> findByUser(User user);
}
