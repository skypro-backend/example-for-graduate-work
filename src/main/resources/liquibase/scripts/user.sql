-- liquibase formatted sql
-- changeset Mariya:1

create TABLE users (
id INT primary key,
username VARCHAR(255),
password VARCHAR(255),
email VARCHAR(255),
firstName VARCHAR(255),
lastName VARCHAR(255),
phone VARCHAR(255),
role VARCHAR(255),
imageId INT REFERENCES image (id),
);

create TABLE ads (
pk INT primary key,
authorId INT REFERENCES users (id),
imageId INT REFERENCES image (id),
price INT,
title TEXT,
description TEXT,
);

create TABLE comments (
pk INT primary key,
createdAt BIGINT,
text TEXT,
adId INT REFERENCES ads (id),
authorId INT REFERENCES users (id),
);

create TABLE image (
id INT primary key
filePath TEXT,
fileSize BIGINT,
mediaType TEXT,
);