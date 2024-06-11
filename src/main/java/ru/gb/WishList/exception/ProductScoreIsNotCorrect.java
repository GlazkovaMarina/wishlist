package ru.gb.WishList.exception;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="ProductScoreIsNotCorrect", description="Исключение 'Рейтинг не в диапазоне от 0 до 5'")
public class ProductScoreIsNotCorrect extends RuntimeException {
    public ProductScoreIsNotCorrect() {
        super();
    }

    public ProductScoreIsNotCorrect(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProductScoreIsNotCorrect(final String message) {
        super(message);
    }

    public ProductScoreIsNotCorrect(final Throwable cause) {
        super(cause);
    }

}
