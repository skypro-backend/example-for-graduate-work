-- liquibase formatted sql

-- changeset csa21472001:create-comment_entity-table
CREATE TABLE comment_entity
(
    id                       serial PRIMARY KEY,
    price                    integer,
    author_image             text,
    author_first_name        varchar(255),
    created_at               varchar(255),
    text                     text,
    user_entity_id bigint REFERENCES user_entity (id),
    ad_id bigint REFERENCES ad_entity (id),
    image_entity_path text REFERENCES image_entity (file_path)
);