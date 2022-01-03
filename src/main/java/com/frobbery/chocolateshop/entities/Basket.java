package com.frobbery.chocolateshop.entities;

import javax.persistence.*;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Basket() {
    }

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

    public Chocolate getChocolate1() {
        return chocolate1;
    }

    public void setChocolate1(Chocolate chocolate1) {
        this.chocolate1 = chocolate1;
    }

    public Chocolate getChocolate2() {
        return chocolate2;
    }

    public void setChocolate2(Chocolate chocolate2) {
        this.chocolate2 = chocolate2;
    }

    public Chocolate getChocolate3() {
        return chocolate3;
    }

    public void setChocolate3(Chocolate chocolate3) {
        this.chocolate3 = chocolate3;
    }

    public Chocolate getChocolate4() {
        return chocolate4;
    }

    public void setChocolate4(Chocolate chocolate4) {
        this.chocolate4 = chocolate4;
    }

    public Basket(Chocolate chocolate1, Chocolate chocolate2, Chocolate chocolate3, Chocolate chocolate4) {
        this.chocolate1 = chocolate1;
        this.chocolate2 = chocolate2;
        this.chocolate3 = chocolate3;
        this.chocolate4 = chocolate4;
    }
}
