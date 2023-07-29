CREATE TABLE ads
(
    pk_id         SERIAL PRIMARY KEY,
    user_id       INT NOT NULL,
    image_address VARCHAR(255),
    description   VARCHAR(255),
    price         INT,
    title         TEXT

);

CREATE TABLE comments
(
    comment_id     SERIAL PRIMARY KEY,
    ad_id          INT NOT NULL,
    user_id        INT NOT NULL,
    created_time   BIGINT,
    text           VARCHAR(255)
);

CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(255) UNIQUE,
    email         VARCHAR(255) ,
    password      VARCHAR(255) ,
    first_name    VARCHAR(255) ,
    last_name     VARCHAR(255) ,
    phone         VARCHAR(255) ,
    image_path         TEXT,
    pk_id         INT,
    role          VARCHAR(255) ,
    ad_id         INT
);

CREATE TABLE user_images
(
    image_id      SERIAL PRIMARY KEY,
    user_id       INT,
    image_address TEXT
);

CREATE TABLE ads_images
(
    ads_image_id   SERIAL PRIMARY KEY,
    ad_id          INT,
    image_address  TEXT
);