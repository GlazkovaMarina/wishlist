package ru.gb.WishList.entities.enums;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Status - статус подарка
 *
 * @UNBOOKED - свободный, никем не забронированный
 * @BOOKED - забронированный, то есть кто-то планирует подарить данный подарок
 **/


@Tag(name="Status", description="Статус подарка: UNBOOKED, BOOKED")
public enum Status {
    UNBOOKED("Не забронирован"),
    BOOKED("Забронирован");

    @Schema(description = "Идентификатор на кириллице: Не забронирован, забронирован")
    public final String label;

    private Status(@Parameter(description = "Идентификатор на кириллице")String label) {

        this.label = label;
    }

    }