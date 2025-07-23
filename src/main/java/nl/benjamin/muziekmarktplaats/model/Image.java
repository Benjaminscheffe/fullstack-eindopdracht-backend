package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;


@Entity
public class Image {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String fileName;

//    @OneToOne(mappedBy = "image")
//    Beat beat;
//
//    public Beat getBeat() {
//        return beat;
//    }

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image() {}
//
//    public Long getId() {
//        return id;
//    }

    public String getFileName() {
        return fileName;
    }

//    public void setName(String fileName) {
//        this.fileName = fileName;
//    }
}
