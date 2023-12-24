-- liquibase formatted sql

-- changeset vadim:1
CREATE table comments(
    id BIGSERIAL PRIMARY KEY,
    created_at INTEGER,
    text TEXT,
    user_id INTEGER REFERENCES users (id)
)

--changeset antonov333:9
ALTER TABLE comments DROP COLUMN user_id;
ALTER TABLE comments ADD user_id BIGINT;

--changeset antonov333:10
ALTER TABLE comments ADD password TEXT;

--changeset antonov333:11
ALTER TABLE comments DROP COLUMN password;