package com.frobbery.chocolateshop.entities;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String email;

    private String password;

    private Long phoneNumber;

    @OneToOne
    private Basket basket;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public User(String email, String password, Long phoneNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    private boolean hasRole(String roleName) {
        return email.equals(roleName);
    }

    public boolean isWorker() {
        return hasRole("WORKER");
    }

    public boolean isAdmin() {
        return hasRole("ADMIN");
    }

    public boolean isChef() {
        return hasRole("CHEF");
    }
}
