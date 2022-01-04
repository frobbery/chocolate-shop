package com.frobbery.chocolateshop.entities;

public enum Status {
    REGISTERED ("Зарегистрирован"),
    PAID ("Оплачен"),
    GATHERED("Собран"),
    SENT("Отправлен");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return title;
    }
}
