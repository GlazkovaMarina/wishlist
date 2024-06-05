package ru.gb.WishList.domain;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import ru.gb.WishList.validation.PasswordMatches;
import ru.gb.WishList.validation.ValidEmail;

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
@PasswordMatches
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotEmpty(message = "Поле _фамилия_ не может быть пустым")
    @Column (name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "Поле _имя_ не может быть пустым")
    @Column (name = "first_name", nullable = false)
    private String firstName;

    private String surname;
//    @NotEmpty(message = "Поле _день рождения_ не может быть пустым")
    @Column (nullable = false)
    private LocalDate birthday;

//    @NotEmpty(message = "Поле _номер телефона_ не может быть пустым")
    @Column (nullable = false, unique = true)
    private Long number;

    @ValidEmail
    @NotEmpty(message = "Поле _эдектронная почта_ не может быть пустым")
    @Column (nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Поле _пароль_ не может быть пустым")
    @Column(nullable=false)
    private String password;
    @Column (name = "matching_password",nullable=false)
    private String matchingPassword;

//    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinTable(
//            name="users_roles",
//            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
//            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
//    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy="owner")
    private Set<Gift> gifts;
}
