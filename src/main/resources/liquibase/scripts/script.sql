-- liquibase formatted sql

-- changeSet annabelousova:1
create TABLE users (
    id integer PRIMARY KEY,
    image VARCHAR(300),
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    role VARCHAR(10),
    password VARCHAR(100)
);
create TABLE  ads(
    id integer PRIMARY KEY,
    image VARCHAR(300),
    price integer,
    title VARCHAR(200)
);
create TABLE comments (
    id integer PRIMARY KEY,
    createdAt BIGSERIAL,
    text TEXT
);