package ru.gb.WishList.entities;

import jakarta.persistence.*;
import lombok.Data;
import ru.gb.WishList.entities.enums.Priority;
import ru.gb.WishList.entities.enums.Status;

/**
 * Gift - подарок
 *
 * @id - идентификационный номер подарка в базе данных
 * @comment - уточнения от желающего подарок
 * @priority - приоритет к дарению
 * @status - статус подарка
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
@Table(name = "gifts")
public class Gift{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_id")
    private Long id;
    private String comment;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User owner;
    @ManyToOne
    private Product product;

    // todo: если успею, то добавить контакт, который забронировал подарок.
    // todo: если успею, реализовать базу контактов, которым есть доступ к прочтению списка подарков
    // todo: написать тесты

}
