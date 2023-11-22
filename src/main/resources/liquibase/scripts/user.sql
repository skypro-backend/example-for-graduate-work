-- liquibase formatted sql
-- changeset Mariya:1

CREATE TABLE users (
id INT primary key,
username VARCHAR(255),
password VARCHAR(255),
email VARCHAR(255),
firstName VARCHAR(255),
lastName VARCHAR(255),
phone VARCHAR(255),
role VARCHAR(255),
image VARCHAR(255),
);

CREATE TABLE ads (
pk INT primary key,
author INT,
image VARCHAR(255),
price INT,
title TEXT,
);

CREATE TABLE comments (
pk INT primary key,
author INT,
authorImage VARCHAR(255),
authorFirstName VARCHAR(255),
text TEXT,
);