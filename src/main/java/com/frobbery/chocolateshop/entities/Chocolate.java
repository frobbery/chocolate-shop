package com.frobbery.chocolateshop.entities;

import javax.persistence.*;

@Entity
public class Chocolate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Chocolate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Chocolate(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
