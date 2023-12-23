-- liquibase formatted sql

-- changeset antonov333:6
CREATE table login(
                    id BIGSERIAL PRIMARY KEY,
                    username TEXT,
                    password TEXT
                    ) ;