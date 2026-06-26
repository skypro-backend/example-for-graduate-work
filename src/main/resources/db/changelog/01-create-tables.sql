-- liquibase formatted sql

-- changeset sergey:1
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       phone VARCHAR(255),
                       role VARCHAR(50),
                       image VARCHAR(255)
);

-- changeset sergey:2
CREATE TABLE ads (
                     id SERIAL PRIMARY KEY,
                     author_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                     image VARCHAR(255),
                     price INTEGER,
                     title VARCHAR(255),
                     description TEXT
);

-- changeset sergey:3
CREATE TABLE comments (
                          id SERIAL PRIMARY KEY,
                          author_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                          ad_id INTEGER REFERENCES ads(id) ON DELETE CASCADE,
                          created_at BIGINT,
                          text TEXT
);