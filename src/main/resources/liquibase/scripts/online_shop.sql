--liquibase formatted sql

--changeset ShilovaNatalia:table comments
create table comments
(
    id              serial primary key,
    ads_id          bigint    not null,
    user_profile_id bigint    not null,
    created_at      timestamp not null,
    text            text      not null
);

--changeset ShilovaNatalia:tables users and authorities
create table users
(
    username varchar(50)  not null primary key,
    password varchar(120) not null,
    enabled  boolean      not null
);

create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username)
);

--changeset ShilovaNatalia: table user
drop table if exists "user";
create table "user"
(
    user_profile_id serial primary key,
    email           varchar(50),
    first_name      varchar(50) not null,
    last_name       varchar(50) not null,
    phone           varchar(20) not null,
    reg_date        DATE        not null,
    city            varchar(50),
    avatar          bytea,
    foreign key (email) references users (username),
    constraint unique_email unique (email)
);

--changeset ShilovaNatalia: table ads
drop table if exists ads;
create table ads
(
    ads_id      serial primary key,
    user_id     bigint  not null,
    title       text    not null,
    description text    not null,
    price       integer not null,
    foreign key (user_id) references "user" (user_profile_id)
);


--changeset ShilovaNatalia:comments_add_fk
alter table comments
    add constraint fk_ads foreign key (ads_id) references ads (ads_id);

--changeset ShilovaNatalia:comments_add_fk_to_user
alter table comments
    add constraint fk_ads_user_profiles foreign key (user_profile_id) references "user" (user_profile_id);

--changeset ShilovaNatalia:image_table
create table image
(
    id         serial primary key,
    data       bytea  not null,
    media_type varchar(255)  not null,
    file_size  bigint  not null,
    ads        bigint not null,
    foreign key (ads) references ads (ads_id)
);

