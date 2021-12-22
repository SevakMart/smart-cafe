package am.smartcafe.data_access.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import am.smartcafe.data_access.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
