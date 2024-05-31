//package ru.gb.WishList.domain;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
///**
// * Gift - подарок
// *
// * @id - идентификационный номер подарка в базе данных
// * @comment - уточнения от желающего подарок
// * @priority - приоритет к дарению
// * @status - статус подарка
// *
// * @@Data   -  дает нам геттеры для всех полей, сеттеры для всех нефинальных полей, правильные реализации toString,
// *            equals и hashCode, охватывающие все поля класса,
// *            а также конструктор для всех финальных полей
// * @@Entity -  представляет собой таблицу, хранящуюся в базе данных. Каждый экземпляр сущности представляет
// *            строку в таблице
// * @@Table  -  задание имени таблицы в базе данных
// * @@Id     -  определяет первичный ключ. Мы можем генерировать идентификаторы разными способами,
// *            которые указаны в аннотации @GeneratedValue
// * @@Column -  при необходимости задаем имя поля в таблице, возможность хранения null и уникальность
// **/
//
//@Data
//@Entity
//@Table(name = "gifts")
//public class Gift extends Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String comment;
//    @Column(nullable = false)
//    private Enum<Priority> priority;
//    @Column (nullable = false)
//    private Enum<Status> status;
//    // добавить ли сюда желающего подарка и кто забронил его?
//}
