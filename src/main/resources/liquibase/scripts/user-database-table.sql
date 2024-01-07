-- liquibase formatted sql

-- changeset antonov333:2
    CREATE table users(
    id  BIGSERIAL PRIMARY KEY,
        name TEXT,
        surname TEXT,
        phone_number TEXT,
        email TEXT,
        age int,
        gender TEXT,
        role TEXT,
        avatar TEXT,
        user_birthday int );

-- changeset antonov333:12
ALTER table users ADD password TEXT;

--changeset antonov333:14
ALTER table users DROP column id;
ALTER table users ADD column id SERIAL PRIMARY key;