//package ru.gb.WishList.domain;
//
//import java.util.Map;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
///**
// * WishList - список подарков
// * @id - идентификацонный номер в таблице
// * @host - собственник списка
// * @list - список подарков с указанием дарителя (если есть)
// *
// *  @@Data   -  дает нам геттеры для всех полей, сеттеры для всех нефинальных полей, правильные реализации toString,
// *             equals и hashCode, охватывающие все поля класса,
// *             а также конструктор для всех финальных полей
// *  @@Entity -  представляет собой таблицу, хранящуюся в базе данных. Каждый экземпляр сущности представляет
// *             строку в таблице
// *  @@Table  -  задание имени таблицы в базе данных
// *  @@Id     -  определяет первичный ключ. Мы можем генерировать идентификаторы разными способами,
// *             которые указаны в аннотации @GeneratedValue
// *  @@Column -  при необходимости задаем имя поля в таблице, возможность хранения null и уникальность
// */
//
//@Data
//@Entity
//@Table (name = "list")
//public class WishList {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false, unique = true)
//    private User host;
//    private Map<Product, User> list;
//}
