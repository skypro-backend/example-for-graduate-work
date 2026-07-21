CREATE TABLE comments (
    id          SERIAL PRIMARY KEY,
    text        TEXT NOT NULL,
    ad_id       INTEGER NOT NULL REFERENCES ads(id),
    author_id   INTEGER NOT NULL REFERENCES users(id),
    created_at  BIGINT NOT NULL
);