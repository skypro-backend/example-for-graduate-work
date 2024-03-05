-- liquibase formatted sql

-- changeset mvoyteh:1
CREATE TABLE listing
(

    id          BIGSERIAL PRIMARY KEY,
    description varchar(255),
    price       INT,
    title       VARCHAR(255),
    image_id   bigint references image (id),
    author_id   BIGINT references users(id)

);

CREATE TABLE avatar
(

    id        BIGSERIAL PRIMARY KEY,
    file_path  varchar(255),
    file_size  bigint,
    media_type varchar(255),
    data      bytea

);

CREATE TABLE comment
(

    id         BIGSERIAL PRIMARY KEY,
    created_at timestamp,
    text       varchar(255),
    pk_listing      bigint references listing(id),
    author_id  bigint references users(id)

);

CREATE TABLE image
(

    id        BIGSERIAL PRIMARY KEY,
    file_path  varchar(255),
    file_size  bigint,
    media_type varchar(255),
    data      bytea

);

CREATE TABLE users
(

    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    phone      VARCHAR(255),
    role       VARCHAR(255),
    image_id   bigint references image (id)

);