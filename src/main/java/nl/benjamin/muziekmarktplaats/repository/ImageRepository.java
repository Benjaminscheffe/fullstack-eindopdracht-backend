package nl.benjamin.muziekmarktplaats.repository;

import nl.benjamin.muziekmarktplaats.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {}
