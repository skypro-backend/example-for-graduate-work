-- liquibase formatted sql

-- changeset DrogolovaNadezhda: ad_table-1
CREATE TABLE ad_table (
    id SERIAL PRIMARY KEY,
    author_id INTEGER NOT NULL,
    image_id INTEGER,
    price INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (author_id) REFERENCES user_table (id)
);
