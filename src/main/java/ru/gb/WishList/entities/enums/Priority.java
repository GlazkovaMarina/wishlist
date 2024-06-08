package ru.gb.WishList.entities.enums;

/**
 * Priority - приоритет подарка для желателя
 * @HIGH - высокий (хотелось бы получить в подарок в первую очередь)
 * @MIDDLE - средний
 * @LOW - низкий
 **/
public enum Priority {
    HIGH("Высокий"),
    MIDDLE("Средний"),
    LOW("Низкий");

    public final String label;

    private Priority(String label) {
        this.label = label;
    }
}