-- liquibase formatted sql

-- changeset yuzu:1

create table users (
id int8 generated by default as identity,
email varchar(255) not null,
first_name varchar(255) not null,
last_name varchar(255) not null,
password varchar(255) not null,
phone varchar(255) not null,
role varchar(255) not null,
image_id int8,
primary key (id))
