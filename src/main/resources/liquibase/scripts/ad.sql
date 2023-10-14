CREATE TABLE ad_table (
    id SERIAL PRIMARY KEY,
    author_id INTEGER NOT NULL,
    image VARCHAR(255),
    price INTEGER,
    title VARCHAR(255),
    FOREIGN KEY (author_id) REFERENCES user_table (id)
);