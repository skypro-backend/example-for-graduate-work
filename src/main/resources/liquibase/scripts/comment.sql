
CREATE TABLE comment_table
(
   id SERIAL PRIMARY KEY,
   ad_id INTEGER,
   author_id INTEGER,
   created_at timestamp,
   text VARCHAR(1000) NOT NULL,
   FOREIGN KEY (ad_id) REFERENCES ad_table (id),
   FOREIGN KEY (author_id) REFERENCES user_table (id)
);