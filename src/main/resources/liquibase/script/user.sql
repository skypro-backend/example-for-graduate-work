-- liquibase formatted sql

-- changeset csa21472001:create-user_entity-table
CREATE TABLE user_entity
(
    id                  serial PRIMARY KEY,
    username            varchar(255),
    password            varchar(255),
--    email               varchar(255),
    first_name          varchar(255),
    last_name           varchar(255),
    phone               smallint,
    role                varchar(5),
    description         text,
    image_entity_path text REFERENCES image_entity (file_path),
);