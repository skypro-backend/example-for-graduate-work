-- liquibase formatted sql

-- changeSet 11th:1
create table if not exists users
(
    id         serial primary key not null,
    password   text               not null,
    first_name varchar(70)        not null,
    last_name  varchar(70)        not null,
    email      varchar(50) unique not null,
    phone      varchar(15),
    image      text,
    role       varchar(10)
);

create table if not exists adverts
(
    id          serial primary key not null,
    title       varchar(100)       not null,
    description text,
    price       integer,
    image       text,
    user_id     integer references users (id)
);

-- changeSet SepMari:1
create table if not exists comments
(
    id                serial primary key not null,
    author_first_name varchar(70)        not null,
    author_image      varchar(70),
    created_At        integer,
    pk                integer,
    text              varchar(350)       not null
    );