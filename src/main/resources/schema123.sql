CREATE TABLE IF NOT EXISTS users
(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    last_name varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    surname varchar(255),
    birthday  DATE NOT NULL,
    email varchar(255) NOT NULL,
    number BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    price DOUBLE PRECISION,
    image varchar(255),
    description varchar(255),
    score float
);

CREATE TABLE IF NOT EXISTS gifts
(
    gift_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    comment varchar(255),
    priority varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    owner
);

