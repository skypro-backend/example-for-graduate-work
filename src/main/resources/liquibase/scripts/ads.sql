-- liquibase formatted sql

-- changeset rzrazhevskiy:2

CREATE TABLE ads (
                       pk SERIAL PRIMARY KEY,
                       title TEXT NOT NULL,
                       price INT NOT NULL,
                       description TEXT,
                       image TEXT,
                       author INT REFERENCES users (id)
)