CREATE TABLE ads
(
    id  serial PRIMARY KEY ,
    author_id int,
    image BYTEA,
    price int,
    title TEXT,
    constraint fk_author_id foreign key (author_id) references users (id)
);