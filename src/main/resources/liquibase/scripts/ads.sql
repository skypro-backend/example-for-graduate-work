-- liquibase formatted sql

-- changeset afetisov:1

create table ads(
id bigserial primary key,
image varchar,
price int,
title varchar,
description varchar,
author_id bigint references users(id)
)

