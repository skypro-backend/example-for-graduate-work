-- liquibase formatted sql

-- changeSet annabelousova:1
create TABLE users
(
    id         integer PRIMARY KEY,
    image      VARCHAR(300),
    email      VARCHAR(100) NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    phone      VARCHAR(15)  NOT NULL,
    role       VARCHAR(10),
    password   VARCHAR(100)
);
create TABLE ads
(
    id    integer PRIMARY KEY,
    image VARCHAR(300),
    price integer,
    title VARCHAR(200)
);
create TABLE comments
(
    id        integer PRIMARY KEY,
    createdAt BIGSERIAL,
    text      TEXT
);

-- changeSet annabelousova:2
ALTER TABLE comments
    RENAME COLUMN createdat TO created_at;

-- changeSet michailzaretskiy:3
ALTER TABLE ads ADD COLUMN author_id INTEGER REFERENCES users(id);
ALTER TABLE ads ADD COLUMN comments_id INTEGER REFERENCES comments(id);
ALTER TABLE ads ADD COLUMN description TEXT;

-- changeSet vasyanpupkin:1
ALTER TABLE users
    ADD COLUMN login VARCHAR(50) NOT NULL

-- changeSet vasyanpupkin:2
CREATE INDEX users_login_index
    ON users (login)


-- changeSet annabelousova:4
ALTER TABLE comments ADD COLUMN author_id INTEGER REFERENCES users(id);
ALTER TABLE comments ADD COLUMN ad_id INTEGER REFERENCES ads(id);