-- liquibase formatted sql

-- changeset grigorii:create-user_entity-table
CREATE TABLE user_entity (
    id                  serial PRIMARY KEY,
    email               text,
    password            text,
    first_name          text,
    last_name           text,
    phone               text,
    role                int4,
    file_path           text,
    file_size           bigint,
    media_type          text,
    data                oid
);

-- changeset grigorii:create-ad_entity-table
CREATE TABLE ad_entity (
    pk                  serial PRIMARY KEY,
    price               integer,
    title               text,
    description         text,
    file_path           text,
    file_size           bigint,
    media_type          text,
    data                oid,
    user_entity_id      int4,
    ad_entity_id        int4
);

-- changeset grigorii:create-comment_entity-table
CREATE TABLE comment_entity (
    pk                  serial PRIMARY KEY,
    text                text,
    created_at          bigint,
    user_entity_id      int4
);