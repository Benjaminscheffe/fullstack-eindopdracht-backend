package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "beats")
public class Beat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int bpm;
    private int price;

    @OneToOne
    Image image;

    @OneToMany(mappedBy = "beat")
    List<Order> orders;

    @OneToMany(mappedBy = "beat")
    List<Review>  reviews;


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Beat() {}

    public Beat(String title, int bpm, int price) {
        this.title = title;
        this.bpm = bpm;
        this.price = price;
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

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
