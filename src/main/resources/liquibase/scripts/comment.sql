-- liquibase formatted sql

-- changeset krassotin:1

CREATE TABLE comments
(
    id  BIGSERIAL PRIMARY KEY ,
    description varchar(255),

)