INSERT INTO users (last_name, first_name, surname, birthday, email, number)
VALUES ('Ivanov', 'Ivan', 'Ivanovich', (DATE '2015-12-17'), 'qwerty@gmail.com', 79009009090);

INSERT INTO users (last_name, first_name, surname, birthday, email, number)
VALUES ('Kirillov', 'Kirill', 'Kirillovich', (DATE '2010-11-23'), 'asdfg@mail.ru', 79879879988);

INSERT INTO users (last_name, first_name, surname, birthday, email, number)
VALUES ('Petrova', 'Olga', 'Ivanovna', (DATE '2000-09-23'), '123@gmail.com', 71231231212);

INSERT INTO users (last_name, first_name, surname, birthday, email, number)
VALUES ('Sonova', 'Inga', 'Olegovna', (DATE '1995-02-11'), 'qs3y@gmail.com', 79209009090);

INSERT INTO products (name, price, image, description, score)
VALUES ('Смартфон', 34999, '/img/phone.svg', 'Смартфон нового поколения', 4.32);

INSERT INTO products (name, price, image, description, score)
VALUES ('Книга', 999, '/img/book.svg', 'А.С.Пушкин Евгений Онегин', 5);

INSERT INTO products (name, price, image, description, score)
VALUES ('Чайная пара', 13999, '/img/glass.svg', 'Керамическая кружка и блюдце для чая', 4.24);

INSERT INTO products (name, price, image, description, score)
VALUES ('Утюг', 3489, '/img/iron.svg', 'Утюг - легкий, но отлично гладит', 3.12);

INSERT INTO products (name, price, image, description, score)
VALUES ('Дрель', 2588, '/img/drill.svg', 'Дрель стандартная', 4);

INSERT INTO products (name, price, image, description, score)
VALUES ('Игрушка', 858, '/img/toys.svg', 'Игрушка 3+, материал: безопасный пластик', 4.4);

insert into gifts (comment,owner_user_id,priority,product_product_id,status)
values ('Для Олеси',1,'HIGH',6,'UNBOOKED');



