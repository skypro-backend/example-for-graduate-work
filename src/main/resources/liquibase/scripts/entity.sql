-- liquibase formatted sql

-- changeSet andrew:1
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