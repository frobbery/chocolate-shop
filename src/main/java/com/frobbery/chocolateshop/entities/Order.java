package com.frobbery.chocolateshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    private User user;

    @OneToOne
    private Basket basket;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String address;

    public User getUser() {
        return user;
    }

    public Basket getBasket() {
        return basket;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public Order(User user, String address) {
        this.user = user;
        this.basket = user.getBasket();
        this.status = Status.REGISTERED;
        this.address = address;
    }
}
