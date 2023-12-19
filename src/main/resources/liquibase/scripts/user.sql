-- liquibase formatted sql

-- changeset krassotin:1
create table users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    phone      VARCHAR(255),
    role       VARCHAR(255),
    image_id   bigint references image (id)
)