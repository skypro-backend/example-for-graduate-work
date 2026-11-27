--liquibase formatted sql

--changeset author:1
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(16) NOT NULL,
    last_name VARCHAR(16) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    role VARCHAR(10) NOT NULL,
    image VARCHAR(255)
);

--changeset author:2
CREATE TABLE ads (
    id SERIAL PRIMARY KEY,
    title VARCHAR(32) NOT NULL,
    price INTEGER NOT NULL,
    description VARCHAR(64) NOT NULL,
    image VARCHAR(255),
    author_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

--changeset author:3
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    text VARCHAR(64) NOT NULL,
    created_at BIGINT NOT NULL,
    author_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    ad_id INTEGER NOT NULL REFERENCES ads(id) ON DELETE CASCADE
);