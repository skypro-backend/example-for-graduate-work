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
image VARCHAR(255),
adId INT,
);

create TABLE ads (
pk INT primary key,
author INT,
image VARCHAR(255),
price INT,
title TEXT,
description TEXT,
commentId INT
);

create TABLE comments (
pk INT primary key,
author INT,
text TEXT,
adId INT,
);

create TABLE image (
id INT primary key
filePath TEXT,
fileSize BIGINT,
mediaType TEXT,
);