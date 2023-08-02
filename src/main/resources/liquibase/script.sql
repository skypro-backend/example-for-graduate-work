-- liquibase formatted sql

-- changeset lolipis:1
CREATE TABLE users
(
    id            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email         VARCHAR NOT NULL,
    password      VARCHAR NOT NULL,
    first_name    VARCHAR NOT NULL,
    last_name     VARCHAR,
    phone         VARCHAR NOT NULL,
    user_role     VARCHAR NOT NULL DEFAULT 'USER',
    image         VARCHAR
);

CREATE TABLE ads
(
    id            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title         VARCHAR NOT NULL,
    description   VARCHAR,
    price         INTEGER NOT NULL,
    image         VARCHAR NOT NULL,
    author_id     INTEGER REFERENCES users (id)
);

CREATE TABLE comments
(
    id            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    text          VARCHAR NOT NULL,
    public_date    TIMESTAMP NOT NULL,
    ads_id        INTEGER REFERENCES ads (id),
    author_id     INTEGER REFERENCES users (id)
);
