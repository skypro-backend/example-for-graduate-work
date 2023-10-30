-- liquibase formatted sql

-- changeset DrogolovaNadezhda: image-1
create table image
(
    id SERIAL PRIMARY KEY,
    data       bytea,
    file_size  bigint not null,
    media_type varchar(255)
);