package nl.benjamin.muziekmarktplaats.repository;

import nl.benjamin.muziekmarktplaats.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
