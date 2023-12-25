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

-- changeSet michailzaretskiy:5
ALTER TABLE users ADD COLUMN enabled BOOLEAN;

-- changeSet michailzaretskiy:6
ALTER TABLE users DROP COLUMN email;
-- changeSet michailzaretskiy:7
ALTER TABLE users DROP COLUMN enabled;
-- changeSet michailzaretskiy:8
CREATE TYPE user_role AS ENUM('USER','ADMIN');
ALTER TABLE users RENAME COLUMN role TO old_role;
ALTER TABLE users ADD COLUMN role user_role;
-- changeSet michailzaretskiy:9
ALTER TABLE users DROP COLUMN old_role;
-- changeSet michailzaretskiy:10
ALTER TABLE users RENAME COLUMN role TO old_role;
ALTER TABLE users ADD COLUMN role VARCHAR(10);
-- changeSet michailzaretskiy:11
ALTER TABLE users ALTER COLUMN phone DROP NOT NULL;
ALTER TABLE users ALTER COLUMN last_name DROP NOT NULL;
ALTER TABLE users ALTER COLUMN first_name DROP NOT NULL;
-- changeSet michailzaretskiy:12
ALTER TABLE users DROP COLUMN old_role;
Drop TYPE user_role;