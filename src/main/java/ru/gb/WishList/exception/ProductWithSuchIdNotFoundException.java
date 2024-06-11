package ru.gb.WishList.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Исключение 'Подарок с таким идентификатором не найден'")
public final class ProductWithSuchIdNotFoundException extends RuntimeException{

    public ProductWithSuchIdNotFoundException() {
        super();
    }

    public ProductWithSuchIdNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProductWithSuchIdNotFoundException(final String message) {
        super(message);
    }

    public ProductWithSuchIdNotFoundException(final Throwable cause) {
        super(cause);
    }
}
