package am.smartcafe.data_access.repository;

import am.smartcafe.data_access.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends   JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
