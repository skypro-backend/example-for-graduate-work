-- liquibase formatted sql

-- changeset rzrazhevskiy:2

CREATE TABLE ads (
                       pk SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       price INT NOT NULL,
                       description TEXT,
                       image VARCHAR(255),
                       author INT REFERENCES users (id)
)