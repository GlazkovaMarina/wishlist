package ru.gb.WishList.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Исключение 'Пользователь с таким идентификатор не найден'")
public final class UserWithSuchIdNotFoundException extends RuntimeException{

    public UserWithSuchIdNotFoundException() {
        super();
    }

    public UserWithSuchIdNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserWithSuchIdNotFoundException(final String message) {
        super(message);
    }

    public UserWithSuchIdNotFoundException(final Throwable cause) {
        super(cause);
    }
}
