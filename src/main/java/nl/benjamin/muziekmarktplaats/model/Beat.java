package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.jar.Attributes;

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

    @OneToOne
    BeatFile beatFile;

    @OneToMany(mappedBy = "beat")
    List<Order> orders;

    @OneToMany(mappedBy = "beat")
    List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public BeatFile getBeatFile() {
        return beatFile;
    }

    public void setBeatFile(BeatFile beatFile) {
        this.beatFile = beatFile;
    }

    public Beat() {}

    public Beat(String title, int bpm, int price) {
        this.title = title;
        this.bpm = bpm;
        this.price = price;
    }

    public Beat(String title, int bpm, int price, Image image, BeatFile beatFile) {
        this.title = title;
        this.bpm = bpm;
        this.price = price;
        this.image = image;
        this.beatFile = beatFile;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
