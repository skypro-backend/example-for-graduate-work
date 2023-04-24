-- liquibase formatted sql

-- changeset elizaveta:2
CREATE TABLE ads(
    id SERIAL NOT NULL  PRIMARY KEY ,
    user_pk SERIAL REFERENCES users(pk),
    image bytea,
    price integer NOT NULL ,
    title VARCHAR(255) NOT NULL ,
    description VARCHAR(255) NOT NULL
);