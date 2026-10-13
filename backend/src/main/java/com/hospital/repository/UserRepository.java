package com.hospital.repository;

import com.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
<<<<<<< HEAD
    boolean existsByUsername(String username);
=======
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
}
