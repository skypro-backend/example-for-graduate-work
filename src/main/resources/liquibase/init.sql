
CREATE TABLE ads
(
    pk_id         SERIAL PRIMARY KEY,
    user_id       INT NOT NULL,
    image_address VARCHAR(255),
    description   VARCHAR(255),
    price         INT,
    title         TEXT,
    ad_id         BIGINT
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
    user_id       SERIAL PRIMARY KEY,
    login         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    phone         VARCHAR(255) NOT NULL,
    image_path    TEXT NOT NULL,
    pk_id         INT,
    role          VARCHAR(255) NOT NULL,
    ad_id         INT
);

CREATE TABLE user_images
(
    image_id      SERIAL PRIMARY KEY,
    user_id       INT NOT NULL,
    image_address TEXT NOT NULL
);

CREATE TABLE ads_images
(
    ads_image_id   SERIAL PRIMARY KEY,
    ad_id          INT NOT NULL,
    image_address  TEXT NOT NULL
);

