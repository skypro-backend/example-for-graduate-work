-- liquibase formatted sql

-- changeSet andrew:2
CREATE TABLE users
(
    id         SERIAL NOT NULL PRIMARY KEY,
    email      TEXT,
    first_name TEXT,
    last_name  TEXT,
    phone      TEXT,
    reg_date   DATE   NOT NULL,
    id_image   INTEGER,
    password   TEXT,
    username   TEXT
);

-- changeSet martell:3
CREATE TABLE avatars
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER,
    path       STRING
);

-- changeSet martell:4
CREATE TABLE posters
(
    id         SERIAL PRIMARY KEY,
    ads_id     INTEGER,
    path       STRING
);
