package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Beat beat;
    private int score;
    private String comment;

    public Review(){}

    public Review(Long id, Beat beat, int score, String comment) {
        this.id = id;
        this.beat = beat;
        this.score = score;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Beat getBeat() {
        return beat;
    }

    public void setBeat(Beat beat) {
        this.beat = beat;
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
}
