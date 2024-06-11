INSERT INTO users (last_name, first_name, surname, birthday, username, roles,  password)
VALUES ('Ivanov', 'Ivan', 'Ivanovich', (DATE '2015-12-17'), 'user1@mail.ru', 'USER', '$2a$10$6kCoe1AshP.8Gk2kHjjHBusq5nRdNzThw7egOdu6ZM8gWV8R6snhO');

INSERT INTO users (last_name, first_name, surname, birthday, username, roles, password)
VALUES ('Kirillov', 'Kirill', 'Kirillovich', (DATE '2010-11-23'), 'user2@mail.ru', 'USER', '$2a$10$6kCoe1AshP.8Gk2kHjjHBusq5nRdNzThw7egOdu6ZM8gWV8R6snhO');

INSERT INTO users (last_name, first_name, surname, birthday, username, roles, password)
VALUES ('Petrova', 'Olga', 'Ivanovna', (DATE '2000-09-23'), 'user3@mail.ru', 'USER', '$2a$10$6kCoe1AshP.8Gk2kHjjHBusq5nRdNzThw7egOdu6ZM8gWV8R6snhO');

INSERT INTO users (last_name, first_name, surname, birthday, username, roles, password)
VALUES ('Sonova', 'Inga', 'Olegovna', (DATE '1995-02-11'), 'admin@mail.ru', 'ADMIN',  '$2a$10$6kCoe1AshP.8Gk2kHjjHBusq5nRdNzThw7egOdu6ZM8gWV8R6snhO');

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

