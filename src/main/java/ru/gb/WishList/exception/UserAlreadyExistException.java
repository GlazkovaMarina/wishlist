package ru.gb.WishList.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Исключение 'Пользователь с такой электронной почтой уже существует'")
public final class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }

    public UserAlreadyExistException(final Throwable cause) {
        super(cause);
    }
}
