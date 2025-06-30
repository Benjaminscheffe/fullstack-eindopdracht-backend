package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;

    public Order() {}

    public Order(Long id, Date orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

}
