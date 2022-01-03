package com.frobbery.chocolateshop.entities;

import javax.persistence.*;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    private Chocolate chocolate1;

    @OneToOne
    private Chocolate chocolate2;

    @OneToOne
    private Chocolate chocolate3;

    @OneToOne
    private Chocolate chocolate4;
}
