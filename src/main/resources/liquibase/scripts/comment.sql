-- liquibase formatted sql

-- changeset poma:1
CREATE TABLE comment
(

    id         BIGSERIAL PRIMARY KEY,
    created_at timestamp,
    text      varchar(255),
    pk_ad      bigint references ad(id),
    author_id  bigint references users(id)

);