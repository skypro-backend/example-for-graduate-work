CREATE TABLE ads_images
(
    id  integer generated always as identity primary key ,
    ads_id int,
    path TEXT,
    constraint fk_ads_id foreign key (ads_id) references ads (id)
    on delete cascade
);

ALTER TABLE ads DROP COLUMN image;
