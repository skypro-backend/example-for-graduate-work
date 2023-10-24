-- liquibase formatted sql

create table users
(
    id         serial primary key not null,
    username   varchar unique,
    password   varchar,
    first_name varchar,
    last_name  varchar,
    phone      varchar,
    image      varchar,
    role       varchar,
);

create table adverts
(
    id          serial primary key not null,
    user_id     integer references users (id),
    price       integer,
    title       varchar,
    image       varchar,
    description varchar
);

create table comments
(
    id         serial primary key not null,
    author_id  integer references users (id),
    advert_id  integer references adverts (id),
    text       varchar,
    created_at timestamp
);

create table authorities(
    username varchar,
    authority varchar
);