-- liquibase formatted sql

-- changeset rzrazhevskiy:1
CREATE TYPE user_role AS ENUM ('USER', 'ADMIN');
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email TEXT NOT NULL,
                       first_name TEXT NOT NULL,
                       last_name TEXT NOT NULL,
                       phone TEXT NOT NULL,
                       role user_role NOT NULL,
                       image TEXT
)