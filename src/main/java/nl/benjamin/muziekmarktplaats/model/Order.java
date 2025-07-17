package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_number_seq")
    @SequenceGenerator(name = "order_number_seq", initialValue = 2501, allocationSize = 1)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "beat_id")
    Beat beat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public Order() {}

    public Order(LocalDateTime orderDate) {
        //this.orderNumber = id + orderDate.getYear();
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
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
