package ru.gb.WishList.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.WishList.entities.enums.Role;
//import ru.gb.WishList.validation.PasswordMatches;
//import ru.gb.WishList.validation.ValidEmail;

import java.time.LocalDate;

/**
 * User - пользователь
 * id - идентификационный номер пользователя в базе данных
 * lastName - фамилия
 * firstName - имя
 * surname - отчество
 * birthday - день, месяц и год рождения
 * number - номер телефона
 * email - электронная почта
 * wishlist - список желаемых подарков
 * Data   -  дает нам геттеры для всех полей, сеттеры для всех нефинальных полей, правильные реализации toString,
 *            equals и hashCode, охватывающие все поля класса,
 *            а также конструктор для всех финальных полей
 * Entity -  представляет собой таблицу, хранящуюся в базе данных. Каждый экземпляр сущности представляет
 *            строку в таблице
 * Table  -  задание имени таблицы в базе данных
 * Id     -  определяет первичный ключ. Мы можем генерировать идентификаторы разными способами,
 *            которые указаны в аннотации @GeneratedValue
 * Column -  при необходимости задаем имя поля в таблице, возможность хранения null и уникальность
 **/
@Data
@Entity
@Table(name = "users")
@Tag(name="User", description="Пользователь веб-приложения")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Schema(description = "Идентификатор")
    private Long id;
//    @NotEmpty(message = "Поле 'фамилия' не может быть пустым")
    @Column (name = "last_name", nullable = false)
    @Schema(description = "Фамилия")
    private String lastName;

//    @NotEmpty(message = "Поле 'имя' не может быть пустым")
    @Column (name = "first_name", nullable = false)
    @Schema(description = "Имя")
    private String firstName;
    @Schema(description = "Отчество")
    private String surname;
//    @NotEmpty(message = "Поле 'день рождения' не может быть пустым")
    @Column (nullable = false)
    @Schema(description = "День рождения")
    private LocalDate birthday;

    // TODO: если разберусь с безопасностью, то реализовать аутентификацию и с помощью телефона
////    @NotEmpty(message = "Поле 'номер телефона' не может быть пустым")
//    @Column (nullable = false, unique = true)
//    private Long number;

//    @ValidEmail
//    @NotEmpty(message = "Поле 'электронная почта' не может быть пустым")
    @Column (nullable = false, unique = true)
    @Schema(description = "Электронная почта")
    private String username; // Чтобы упростить подключение spring security, название поля: username
//    @NotEmpty(message = "Поле 'пароль' не может быть пустым")
    @Column(nullable=false)
    @Schema(description = "Пароль")
    private String password;


    // TODO: если успею, то вернуть корректную проверку пароля
    //    @Column (name = "matching_password",nullable=false)
    //    private String matchingPassword;

    @OneToMany(mappedBy="owner")
    @Schema(description = "Список подарков")
    private Set<Gift> gifts;

    //    @NotEmpty(message = "Поле 'полномочия' не может быть пустым")
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Коллекция уникальных полномочий")
    private Set<Role> roles;

    @Schema(description = "Указывает, истек ли срок действия учетной записи пользователя")
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Указывает, заблокирован или разблокирован пользователь")
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Schema(description = "Указывает, истек ли срок действия учетных данных пользователя/пароля")
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Указывает, включен или отключен пользователь")
    public boolean isEnabled() {
        return true;
    }

    @Override
    @Schema (description = "Возвращает полномочия, предоставленные пользователю")
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    @Schema(description = "Возвращает имя пользователя, используемое для аутентификации пользователя")
    public String getUsername() {
        return username;
    }
}
