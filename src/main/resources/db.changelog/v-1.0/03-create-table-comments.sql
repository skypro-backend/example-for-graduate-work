CREATE TABLE comments
(
    id  integer generated always as identity primary key,
    author_id int,
    created_at TIMESTAMP,
    pk_ads int,
    text TEXT,
    constraint fk_author_id foreign key (author_id) references users (id) ,
    constraint fk_pk_ads foreign key (pk_ads) references ads (id) on delete cascade
);