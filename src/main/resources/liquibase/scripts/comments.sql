-- liquibase formatted sql

-- changeset rzrazhevskiy:3

CREATE TABLE comments (
                       pk SERIAL PRIMARY KEY,
                       text TEXT NOT NULL,
                       created_at BIGINT NOT NULL,
                       author INT REFERENCES users (id),
                       ad INT REFERENCES ads (pk)
)