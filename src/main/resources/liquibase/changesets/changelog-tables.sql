-- Создание таблицы Ad
CREATE TABLE Ad (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price INTEGER,
    description TEXT,
    user_id BIGINT,
    image BYTEA
);

-- Создание таблицы Comment
CREATE TABLE Comment (
    id SERIAL PRIMARY KEY,
    text TEXT,
    ad_id BIGINT,
    user_id BIGINT
);

-- Создание таблицы User
CREATE TABLE finalUser (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(255),
    role VARCHAR(255),
    CONSTRAINT unique_username UNIQUE (username)
);