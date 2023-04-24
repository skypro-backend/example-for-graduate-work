-- liquibase formatted sql

-- changeset elizaveta:3
CREATE TABLE comments(
    id SERIAL NOT NULL PRIMARY KEY ,
    comment_text VARCHAR(255) NOT NULL ,
    user_pk SERIAL REFERENCES users(pk),
    ads_id SERIAL REFERENCES ads(id),
    created_at timestamp
);