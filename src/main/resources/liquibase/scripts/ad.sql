-- liquibase formatted sql

-- changeset poma:1
CREATE TABLE ad
(

    id          BIGSERIAL PRIMARY KEY,
    description varchar(255),
    price       INT,
    title       VARCHAR(255),
    image_id   bigint references image (id),
    author_id   BIGINT REFERENCES users(id)

);