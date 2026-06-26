-- liquibase formatted sql

-- changeset sergey:4
ALTER TABLE users ADD COLUMN image_data BYTEA;

-- changeset sergey:5
ALTER TABLE ads ADD COLUMN image_data BYTEA;