package ru.gb.WishList.domain;

/**
 * Status - статус подарка
 *
 * @UNBOOKED - свободный, никем не забронированный
 * @BOOKED - забронированный, то есть кто-то планирует подарить данный подарок
 **/

public enum Status {
    UNBOOKED("Не забронирован"),
    BOOKED("Забронирован");

    public final String label;

    private Status(String label) {
        this.label = label;
    }

    }