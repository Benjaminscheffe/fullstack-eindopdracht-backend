package nl.benjamin.muziekmarktplaats.repository;

import nl.benjamin.muziekmarktplaats.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
