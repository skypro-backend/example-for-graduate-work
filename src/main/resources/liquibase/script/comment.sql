-- liquibase formatted sql

-- changeset csa21472001:create-comment_entity-table
CREATE TABLE comment_entity
(
    id                       serial PRIMARY KEY,
    price                    integer,
    created_at               TIMESTAMP(3),
    text                     text,
    user_entity_id bigint REFERENCES user_entity (id),
    ad_id bigint REFERENCES ad_entity (id),
);