package nl.benjamin.muziekmarktplaats.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static int orderNumber = 2501;
    private Date orderDate;

    public Order() {}

    public Order(Long id, Date orderDate) {
        this.id = id;
        orderNumber = orderNumber++;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public static int getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
