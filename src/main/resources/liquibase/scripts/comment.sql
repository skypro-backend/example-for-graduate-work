-- liquibase formatted sql

-- changeset afetisov:1


create table comments(
id bigserial primary key,
created_at bigint,
text varchar,
ad_id bigint references ads(id),
author_id bigint references users(id)

)