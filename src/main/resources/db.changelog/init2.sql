ALTER TABLE users
    DROP COLUMN role;

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR  NOT NULL UNIQUE
);

CREATE TABLE users_roles
(
    user_id INT REFERENCES users (id),
    role_id INT REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);