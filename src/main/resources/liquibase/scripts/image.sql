-- liquibase formatted sql

-- changeset poma:1
CREATE TABLE image
(

    id        BIGSERIAL PRIMARY KEY,
    file_path  varchar(255),
    file_size  bigint,
    media_type varchar(255),
    data      bytea

);