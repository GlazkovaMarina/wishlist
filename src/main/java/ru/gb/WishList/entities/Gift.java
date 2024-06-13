package ru.gb.WishList.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Data;
import ru.gb.WishList.entities.enums.Priority;
import ru.gb.WishList.entities.enums.Status;

/**
 * Gift - подарок
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
@Tag(name="Gift", description="Подарок")
public class Gift{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_id")
    @Schema(description = "Идентификатор подарка")
    private Long id;
    @Schema(description = "Комментарий хозяина подарка")
    private String comment;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Приоритет подарка")
    private Priority priority;
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Статус подарка")
    private Status status;
    @ManyToOne
    @Schema(description = "Хозяин подарка")
    private User owner;
    @ManyToOne
    @Schema(description = "Подарок")
    private Product product;

    // todo: если успею, то добавить контакт, который забронировал подарок.
    // todo: если успею, реализовать базу контактов, которым есть доступ к прочтению списка подарков

}
