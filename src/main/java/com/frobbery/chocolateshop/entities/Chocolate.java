package com.frobbery.chocolateshop.entities;

import javax.persistence.*;

@Entity
public class Chocolate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String name;

    public Integer quantity;
}
