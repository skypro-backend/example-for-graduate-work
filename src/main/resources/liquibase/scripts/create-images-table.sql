CREATE TABLE images (
    id          VARCHAR(255) PRIMARY KEY,
    file_path   VARCHAR(255) NOT NULL,
    file_size   BIGINT NOT NULL,
    media_type  VARCHAR(255) NOT NULL,
    ad_id       INTEGER REFERENCES ads(id),
    user_id     INTEGER UNIQUE REFERENCES users(id)
);