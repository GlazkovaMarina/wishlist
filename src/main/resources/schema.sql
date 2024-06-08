CREATE TABLE IF NOT EXISTS users
(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    last_name varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    surname varchar(255),
    birthday  DATE NOT NULL,
    username varchar(255) NOT NULL UNIQUE,
    role varchar(255) NOT NULL CHECK (role in ('ADMIN','USER')),
--    number BIGINT NOT NULL UNIQUE,
    password varchar(255) NOT NULL
--    matching_password varchar(255) NOT NULL
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
    gift_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment varchar(255),
    priority varchar(255) NOT NULL CHECK (priority in ('HIGH','MIDDLE','LOW')),
    status varchar(255) NOT NULL CHECK (status in ('UNBOOKED','BOOKED')),
    owner_user_id BIGINT,
    product_product_id BIGINT
);

create table roles (id bigint not null, name varchar(255), primary key (id));
create table users_roles (users_user_id bigint not null, roles_id bigint not null, primary key (users_user_id, roles_id));

alter table if exists gifts add constraint FKcjy6ww1d27s8a0ptpmoy9s4w0 foreign key (owner_user_id) references users;
alter table if exists gifts add constraint FKtij8ymv973egpcudufvf5xkj6 foreign key (product_product_id) references products;
alter table if exists users_roles add constraint FK26khgr1awevy3w2tyjdmo3dgn foreign key (roles_id) references roles;
alter table if exists users_roles add constraint FKmaps3ffbyjaxkt50q1c7s7v5j foreign key (users_user_id) references users;
