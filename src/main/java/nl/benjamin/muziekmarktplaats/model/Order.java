package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    private static int counter = 2501;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int orderNumber;
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "beat_id")
    Beat beat;

    public Beat getBeat() {
        return beat;
    }

    public void setBeat(Beat beat) {
        this.beat = beat;
    }

    public Order() {}

    public Order(Date orderDate) {
        this.orderNumber = counter++;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
