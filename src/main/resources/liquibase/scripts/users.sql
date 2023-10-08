-- liquibase formatted sql

-- changeset AlexeyKutin:1
CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(32) NOT NULL,
    first_name VARCHAR(16) NOT NULL,
    last_name VARCHAR(16) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL,
    avatar_path VARCHAR(200),
    avatar_size INT4,
    avatar_type VARCHAR(25),
    pwd_hash VARCHAR(32) NOT NULL,
    user_role VARCHAR(10) NOT NULL
);
