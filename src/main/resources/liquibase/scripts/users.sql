-- liquibase formatted sql

-- changeset elizaveta:1
CREATE TABLE users (
    pk SERIAL NOT NULL PRIMARY KEY ,
    first_name VARCHAR(255) NOT NULL ,
    last_name VARCHAR(255) NOT NULL ,
    username VARCHAR(255) NOT NULL ,
    phone VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    user_role VARCHAR(255) NOT NULL ,
    image bytea
);