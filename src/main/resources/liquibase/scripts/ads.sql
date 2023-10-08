-- liquibase formatted sql

-- changeset AlexeyKutin:1
CREATE TABLE ads
(
    ad_id          SERIAL PRIMARY KEY,
    author_id      INT4         NOT NULL,
    ad_price       INT4         NOT NULL CHECK ( ad_price > 0),
    ad_title       VARCHAR(32)  NOT NULL,
    ad_description VARCHAR(16)  NOT NULL,
    image_path     VARCHAR(200),
    CONSTRAINT author_id_fkey FOREIGN KEY (author_id) REFERENCES users(user_id)
);