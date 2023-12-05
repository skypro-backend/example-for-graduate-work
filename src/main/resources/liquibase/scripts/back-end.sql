--liquibase formatted sql

--changeset backEndAdmin:1
create table if not exists users
(
    id serial not null PRIMARY KEY,
    username text,
    first_name text,
    last_name text,
    password text,
    phone text,
    role text,
    image_id int
);


--changeset backEndAdmin:2
create table if not exists images
(
    id serial not null PRIMARY KEY,
    media_type text,
    data bytea
);

--changeset backEndAdmin:3
create table if not exists comments
(
    pk serial not null PRIMARY KEY,
    author_id int,
    author_image_id int,
    author_first_name text,
    created_at timestamp,
    text text,
    ad_id int
);

--changeset backEndAdmin:4
create table if not exists ads
(
    pk bigserial not null PRIMARY KEY,
    author_id int,
    image_id int,
    price int,
    title text,
    description text
);
