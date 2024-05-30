CREATE TABLE IF NOT EXISTS users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    last_name varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    surname varchar(255),
    birthday  DATE NOT NULL,
    email varchar(255) NOT NULL,
    number BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    price BigDecimal,
    link varchar(255),
    imgLink varchar(255),
    description varchar(255),
    score float
);