package ru.gb.WishList.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="GiftWithSuchIdNotFoundException", description="Исключение 'Подарок с таким идентификатором не найден'")
public final class GiftWithSuchIdNotFoundException extends RuntimeException{

    public GiftWithSuchIdNotFoundException() {
        super();
    }

    public GiftWithSuchIdNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GiftWithSuchIdNotFoundException(final String message) {
        super(message);
    }

    public GiftWithSuchIdNotFoundException(final Throwable cause) {
        super(cause);
    }
}
