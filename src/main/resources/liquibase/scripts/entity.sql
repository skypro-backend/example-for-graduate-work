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

--changeset mara:1
create table ads
(
    id          SERIAL NOT NULL PRIMARY KEY,
    title       TEXT,
    price       INTEGER,
    description TEXT,
    image_id    INTEGER,
    author_id   INTEGER REFERENCES users (id)
);

-- changeSet igor:1
Create TABLE comment
(
    id SERIAL NOT NULL PRIMARY KEY,
    author INTEGER,
    text TEXT,
    createdAt TEXT
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
