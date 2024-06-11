package ru.gb.WishList.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

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
@Schema(description = "Товар")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @Schema(description = "Идентификатор")
    private Long id;
    @Column (nullable = false)
    @Schema(description = "Название товара")
    private String name;
    @Schema(description = "Стоимость товара")
    private BigDecimal price;
    @Schema(description = "Путь до фотографии товара")
    private String image;
    @Schema(description = "Описание товара")
    private String description;
    @Schema(description = "Рейтинг товара")
    private Float score;
    @OneToMany(mappedBy="product")
    @Schema(description = "Товар в списке подарков")
    private Set<Gift> gifts;
}