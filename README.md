# Дипломный проект 'Разработка высокоэффективного бекенд-сервиса для управления списком желаемых подарков с использованием Spring Boot'

**Выполнила**: Глазкова Марина Влади мировна

**Email**: glazkova.mv@bk.ru

**Телеграмм**: https://t.me/@Marina_Glazkova

## Описание
Веб-приложение, позволяющее зарегистрированному пользователю в минимальной версии маркетплейса сформировать список подарков из имеющегося набора товаров и отправить его своим друзьям и знакомым.

## Роли и их возможности

1. Анонимный пользователь

    Возможности: 
   * Доступ к стартовой странице ("/" , "/index");
   * Доступ к странице регистрации ("/registration");
   * Доступ к странице входа ("/login").

2. Обычный пользователь (USER)

    Возможности:
   * Доступ к личному кабинету ("/personal_office/**");
   * Редактирование части личных данных ("/correct_info/**");
   * Доступ к товарам маркетплейса ("/goods/**");
   * Доступ к карточке выбранного товара ("/card_item/**");
   * Доступ к списку подарков ("/wishlist/**");
   * Доступ к карточке подарка ("/card_present/**");
   * Создание подарка ("/new_present/**");
   * Редактирование подарка ("/edit_present/**");

3. Администратор (ADMIN):
    
    Возможности:
    * Доступ к личному кабинету ("/personal_office/**");
    * Редактирование части личных данных ("/correct_info/**");
    * Доступ к товарам маркетплейса ("/goods/**");
    * Доступ к карточке выбранного товара ("/card_item/**");
    * Создание нового товара ("/new_item/**");
    * Редактирование выбранного товара ("/edit_item/**").

## Тестовые пользователи

Для тестирования веб-приложения можно воспользоваться заранее зарегистрированными пользователями. Для входа потребуется почта и пароль пользователя:

1. USER: user1@mail.ru password
2. USER: user2@mail.ru password
3. USER: user3@mail.ru password
4. ADMIN: admin@mail.ru password

В зависимости от роли выбранного пользователя Вам будет доступен разный функционал веб-сайта (смотри выше).

## Запуск
После запуска приложения перейдите в браузер и введите следующий URL: http://localhost:8080/

## Веб-приложение находится на стадии разработки