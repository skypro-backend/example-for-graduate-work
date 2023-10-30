-- liquibase formatted sql

-- changeset DrogolovaNadezhda: user-1
CREATE TABLE user_table (
  id SERIAL PRIMARY KEY,
  email VARCHAR(32) NOT NULL,
  password VARCHAR(255),
  first_name VARCHAR(32),
  last_name VARCHAR(32),
  phone VARCHAR,
  role VARCHAR(255),
  image INT
);