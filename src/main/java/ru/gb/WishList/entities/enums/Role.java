package ru.gb.WishList.entities.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;


@Schema(description = "Полномочия, предоставленные объекту аутентификации: USER или ADMIN")
public enum Role implements GrantedAuthority
{
    USER,
    ADMIN;

    @Override
    public String getAuthority()
    {
        return name();
    }
}