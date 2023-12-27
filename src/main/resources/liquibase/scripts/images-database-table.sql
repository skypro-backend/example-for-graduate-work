-- liquibase formatted sql

-- changeset antonov333:7
CREATE table images(

    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT,

    ad_id BIGINT,

    picture_type TEXT,

    file_path TEXT,

    file_size BIGINT,

    media_type TEXT,

    data bytea
);

-- changeset antonov333:8
ALTER table images DROP COLUMN data;
ALTER table images ADD data oid;