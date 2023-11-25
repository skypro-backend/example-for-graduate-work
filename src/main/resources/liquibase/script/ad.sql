-- liquibase formatted sql

-- changeset csa21472001:create-ad_entity-table
CREATE TABLE ad_entity
(
    id                  serial PRIMARY KEY,
    price               integer,
    title               text,
    description         text,
    user_entity_id bigint REFERENCES user_entity (id),
    image_entity_path text REFERENCES image_entity (file_path),
);