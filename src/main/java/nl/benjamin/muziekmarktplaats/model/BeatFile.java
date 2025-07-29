package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "beatfiles")
public class BeatFile {

    @Id
    private String fileName;

    public BeatFile(String fileName) {
        this.fileName = fileName;
    }

    public BeatFile() {}

    public String getFileName() {
        return fileName;
    }
}
