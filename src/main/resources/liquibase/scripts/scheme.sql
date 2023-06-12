-- liquibase formatted sql

-- changeSet 11th:1
create table if not exists users
(
    id         serial primary key not null,
    password   text               not null,
    first_name varchar(70)        not null,
    last_name  varchar(70)        not null,
    email      varchar(50) unique not null,
    phone      varchar(15),
    image      text,
    role       varchar(10)
);

create table if not exists adverts
(
    id          serial primary key not null,
    title       varchar(100)       not null,
    description text,
    price       integer,
    image       text,
    user_id     integer references users (id)
);

-- changeSet SepMari:1
create table if not exists comments
(
    id                serial primary key not null,
    author_id         integer references users (id),
    created_at        integer,
    pk_id             integer references adverts (id),
    text              text               not null
    );

-- changeSet akmeevd:1
alter table comments drop column pk_id;
alter table comments add column advert_id integer references adverts (id);

-- changeSet 11th:2
alter table comments drop column created_at;
alter table comments add column created_at timestamp;