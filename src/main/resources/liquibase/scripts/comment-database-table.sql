-- liquibase formatted sql

-- changeset vadim:1
CREATE table comments(
    id BIGSERIAL PRIMARY KEY,
    created_at INTEGER,
    text TEXT,
    user_id INTEGER REFERENCES users (id)
)