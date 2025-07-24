package nl.benjamin.muziekmarktplaats.repository;

import nl.benjamin.muziekmarktplaats.model.BeatFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeatFileRepository extends JpaRepository<BeatFile, String> {}
