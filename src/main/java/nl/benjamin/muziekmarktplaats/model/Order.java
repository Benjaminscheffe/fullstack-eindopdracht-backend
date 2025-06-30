package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Beat beat;
    private User user;
    private Date orderDate;

    public Order() {}

    public Order(Long id, Beat beat, User user, Date orderDate) {
        this.id = id;
        this.beat = beat;
        this.user = user;
        this.orderDate = orderDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
