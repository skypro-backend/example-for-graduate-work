-- liquibase formatted sql

-- changeset hellhorseman:1
CREATE TABLE users
(
    id         BIGINT NOT NULL,
    user_name  TEXT,
    password   TEXT,
    first_name TEXT,
    last_name  TEXT,
    phone      TEXT,

    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE role
(
    role_id    BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    role       TEXT
);

CREATE TABLE avatar
(
    avatar_id  BIGSERIAL NOT NULL,
    data       bytea
);
