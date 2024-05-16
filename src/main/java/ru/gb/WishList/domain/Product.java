package ru.gb.WishList.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Product - товар
 *
 * @id - идентификационный номер товара в базе данных
 * @name - название
 * @price - стоимость
 * @link - ссылка на товар в маркетплейсе
 * @imgLink - ссылка на фото товара
 * @description - описание товара
 * @score - оценка (рейтинг)
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
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String name;
    private Double price;
    private String link;
    private String imgLink;
    private String description;
    private Float score;
}