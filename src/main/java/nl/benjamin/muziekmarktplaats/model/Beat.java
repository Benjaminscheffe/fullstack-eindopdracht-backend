package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;

@Entity
@Table(name = "beats")
public class Beat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;


    public Beat() {}
    public Beat(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
