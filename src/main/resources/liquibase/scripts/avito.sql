-- liquibase formatted sql

-- changeset neo:1
create table users (
    id bigserial primary key,
    email varchar(255) not null unique,
    first_name varchar(100) not null,
    last_name varchar(100),
    phone varchar(30),
    image varchar(255),
    role varchar(20) not null
);

-- changeset skypro:4
alter table users add column if not exists password varchar(255);

-- changeset neo:2
create table ads(
  id bigserial primary key,
  title varchar(100) not null,
  description varchar(1000),
  price integer not null,
  image varchar(255),
  author_id bigint not null,
  constraint fk_ads_user foreign key (author_id) references users(id)
);

-- changeset neo:3
create table comments (
    id bigserial primary key,
    text varchar(500) not null,
    created_at timestamp not null default current_timestamp,
    author_id bigint not null,
    ad_id bigint not null,
    constraint fk_comments_user foreign key (author_id) references users(id),
    constraint fk_comments_ad foreign key (ad_id) references ads(id)
);