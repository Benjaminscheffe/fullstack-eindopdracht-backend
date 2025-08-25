package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderNumber;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "beat_id")
    Beat beat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }


    public Order() {}

    public Order(LocalDateTime orderDate) {
        //this.orderNumber = id + orderDate.getYear();
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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
}
