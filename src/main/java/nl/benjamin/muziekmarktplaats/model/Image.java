package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;


@Entity
@Table(name = "images")
public class Image {

    @Id
    private String fileName;

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image() {}

    public String getFileName() {
        return fileName;
    }

}
