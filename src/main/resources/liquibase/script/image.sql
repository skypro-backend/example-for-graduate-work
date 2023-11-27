-- liquibase formatted sql

-- changeset csa21472001:create-image_entity-table
CREATE TABLE image_entity
(
    id                       serial PRIMARY KEY,
    file_path                integer,
    file_size                varchar(255),
    media_type               varchar(255),
    date                     bytea,
);
