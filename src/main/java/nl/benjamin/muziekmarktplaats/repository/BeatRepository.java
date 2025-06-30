package nl.benjamin.muziekmarktplaats.repository;

import nl.benjamin.muziekmarktplaats.model.Beat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeatRepository extends JpaRepository<Beat, Long> {}
