package ru.gb.WishList.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * User - пользователь
 *
 * @id - идентификационный номер пользователя в базе данных
 * @lastName - фамилия
 * @firstName - имя
 * @surname - отчество
 * @birthday - день, месяц и год рождения
 * @number - номер телефона
 * @email - электронная почта
 * @wishlist - список желаемых подарков
 *
 * @@Data   -  дает нам геттеры для всех полей, сеттеры для всех нефинальных полей, правильные реализации toString,
 *            equals и hashCode, охватывающие все поля класса,
 *            а также конструктор для всех финальных полей
 * @@Entity -  представляет собой таблицу, хранящуюся в базе данных. Каждый экземпляр сущности представляет
 *            строку в таблице
 * @@Table  -  задание имени таблицы в базе данных
 * @@Id     -  определяет первичный ключ. Мы можем генерировать идентификаторы разными способами,
 *            которые указаны в аннотации @GeneratedValue
 * @@Column -  при необходимости задаем имя поля в таблице, возможность хранения null и уникальность
 **/
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "last_name", nullable = false)
    private String lastName;
    @Column (name = "first_name", nullable = false)
    private String firstName;
    private String surname;
    @Column (nullable = false)
    private LocalDate birthday;
    @Column (nullable = false, unique = true)
    private Long number;
    @Column (nullable = false, unique = true)
    private String email;
}
