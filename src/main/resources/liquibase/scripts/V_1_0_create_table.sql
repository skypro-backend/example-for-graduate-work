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
