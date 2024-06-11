package ru.gb.WishList.entities.enums;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Priority - приоритет подарка для хозяина списка подарков
 * @HIGH - высокий (хотелось бы получить в подарок в первую очередь)
 * @MIDDLE - средний
 * @LOW - низкий
 **/
@Schema(description = "Приоритет подарка: HIGH, MIDDLE, LOW", example = "MIDDLE")
public enum Priority {
    HIGH("Высокий"),
    MIDDLE("Средний"),
    LOW("Низкий");

    @Schema(description = "Идентификатор на кириллице: Высокий, средний или низкий")
    public final String label;

    private Priority(@Parameter(description = "Идентификатор на кириллице")String label) {

        this.label = label;
    }
}