CREATE TABLE users
(
    id  serial PRIMARY KEY ,
    email TEXT,
    first_name TEXT,
    last_name TEXT,
    phone TEXT,
    reg_date TIMESTAMP,
    city TEXT,
    image BYTEA
);