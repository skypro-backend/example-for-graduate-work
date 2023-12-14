-- liquibase formatted sql

-- changeset syutins:1

CREATE TABLE ads (
  ad_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   author_id INTEGER,
   image_id VARCHAR(255),
   price INTEGER,
   title VARCHAR(255),
   comment_id INTEGER,
   CONSTRAINT pk_ads PRIMARY KEY (ad_id)
);

CREATE TABLE avatars (
  avatar_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   file_size BIGINT,
   media_type VARCHAR(255),
   file_path VARCHAR(255),
   data OID,
   CONSTRAINT pk_avatars PRIMARY KEY (avatar_id)
);

CREATE TABLE comments (
  comment_id INTEGER NOT NULL,
   author_id INTEGER,
   image_str VARCHAR(255),
   author_name VARCHAR(255),
   created_at BIGINT,
   comment_text VARCHAR(255),
   CONSTRAINT pk_comments PRIMARY KEY (comment_id)
);

CREATE TABLE users (
  user_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   email VARCHAR(255),
   user_name VARCHAR(255),
   password VARCHAR(255),
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   phone VARCHAR(255),
   role VARCHAR(255) NOT NULL,
   image_id VARCHAR(255),
   CONSTRAINT pk_users PRIMARY KEY (user_id)
);