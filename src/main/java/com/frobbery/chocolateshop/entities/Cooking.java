package com.frobbery.chocolateshop.entities;

import javax.persistence.*;

@Entity
public class Cooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Cooking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    private Chocolate chocolate;

    private Integer quantity;

    public Chocolate getChocolate() {
        return chocolate;
    }

    public void setChocolate(Chocolate chocolate) {
        this.chocolate = chocolate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Cooking(Chocolate chocolate, Integer quantity) {
        this.chocolate = chocolate;
        this.quantity = quantity;
    }
}
