package ru.gb.WishList.domain;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
//    @NotBlank(message = "Поле _фамилия_ не может быть пустым")
    @Column (name = "last_name", nullable = false)
    private String lastName;
//    @NotBlank(message = "Поле _имя_ не может быть пустым")
    @Column (name = "first_name", nullable = false)
    private String firstName;
    private String surname;
//    @NotBlank(message = "Поле _день рождения_ не может быть пустым")
    @Column (nullable = false)
    private LocalDate birthday;
//    @NotBlank(message = "Поле _номер телефона_ не может быть пустым")
    @Column (nullable = false, unique = true)
    private Long number;
//    @NotBlank(message = "Поле _электронная почта_ не может быть пустым")
    @Column (nullable = false, unique = true)
    private String email;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "gift_id", referencedColumnName = "id")
    @OneToMany(mappedBy="owner")
    private Set<Gift> gifts;
}
