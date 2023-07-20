
CREATE TABLE ads
(
    pk_id         BIGSERIAL PRIMARY KEY,
    user_id       BIGINT NOT NULL,
    image_address VARCHAR(255),
    description   VARCHAR(255),
    price         INT,
    title         TEXT,
    ad_id         BIGINT
);

CREATE TABLE comments
(
    comment_id     BIGSERIAL PRIMARY KEY,
    ad_id          BIGINT NOT NULL,
    user_id        BIGINT NOT NULL,
    created_time   BIGINT NOT NULL,
    text           TEXT NOT NULL
);

CREATE TABLE users
(
    user_id       BIGSERIAL PRIMARY KEY,
    login         TEXT NOT NULL,
    password      TEXT NOT NULL,
    first_name    TEXT NOT NULL,
    last_name     TEXT NOT NULL,
    phone         TEXT NOT NULL,
    pk_id         BIGINT,
    role          TEXT NOT NULL,
    image         TEXT NOT NULL,
    ad_id         BIGINT
);

