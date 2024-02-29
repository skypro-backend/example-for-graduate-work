-- liquibase formatted sql

-- changeset afetisov:1

create table users(
id bigserial primary key,
username varchar,
password varchar,
first_name varchar,
last_name varchar,
phone varchar,
role varchar,
image varchar
)




