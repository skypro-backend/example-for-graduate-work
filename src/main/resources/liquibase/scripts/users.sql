-- liquibase formatted sql

-- changeset rzrazhevskiy:1
CREATE TYPE user_role AS ENUM ('USER', 'ADMIN');
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(32) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(16) NOT NULL,
                       last_name VARCHAR(16) NOT NULL,
                       phone VARCHAR(18) NOT NULL,
                       role user_role DEFAULT 'USER',
                       image VARCHAR(255)
);
CREATE CAST (character varying AS user_role) WITH INOUT AS ASSIGNMENT;