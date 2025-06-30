package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;


@Entity

@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
