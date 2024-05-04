package ru.gb.WishList.domain;

/**
 * Status - статус подарка
 *
 * UNBOOKED - свободный, никем не забронированный
 * BOOKED - забронированный, то есть кто-то планирует подарить данный подарок
 **/

public enum Status {
    UNBOOKED,
    BOOKED
}