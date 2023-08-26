--liquibase formatted sql

--changeset byNikolai:1

create table ads
(
id serial primary key,
author_id integer reference users(id),
image_id integer reference image(id),
title varchar (200) not null,
description text,
price integer not null
);

create table avatar
(
id serial primary key,
media_type varchar(300),
file_size bigint,
data bytea
);

create table comment
(
id serial primary key,
ads_id integer reference ads(id),
author_id integer reference users(id),
created_at timestamp not null,
text text not null

);

create table image
(
id serial primary key,
media_type varchar(300),
file_size bigint,
data bytea
);

create table users
(
id serial primary key,
username varchar(200) unique not null,
first_name varchar(200) not null,
last_name varchar(200) not null,
phone varchar(200) not null,
password varchar(300) not null,
enabled boolean,
avatar_id integer reference avatar(id),
role varchar(200)
);