-- liquibase formatted sql

-- changeset poma:1
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    phone      VARCHAR(255),
    role       INTEGER,
    avatar_id   bigint references avatar (id)

);