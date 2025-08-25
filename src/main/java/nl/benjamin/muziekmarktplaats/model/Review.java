package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "beat_id")
    Beat beat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public void setBeat(Beat beat) {
        this.beat = beat;
    }

    public Beat getBeat() {
        return beat;
    }

    public Review(){}

    public Review(int score, String comment) {
        this.score = score;
        this.comment = comment;
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }
}
