-- liquibase formatted sql

-- changeset AlexeyKutin:1
CREATE TABLE comments
(
    comment_id   SERIAL PRIMARY KEY,
    ad_id        INT4        NOT NULL,
    author_id    INT4        NOT NULL,
    comment_text VARCHAR(64) NOT NULL,
    created_at   TIMESTAMP   NOT NULL,
    CONSTRAINT author_id_fkey FOREIGN KEY (author_id) REFERENCES users (user_id),
    CONSTRAINT ad_id_fkey FOREIGN KEY (ad_id) REFERENCES ads (ad_id)
);