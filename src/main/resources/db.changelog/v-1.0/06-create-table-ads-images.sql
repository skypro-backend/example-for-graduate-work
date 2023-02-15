CREATE TABLE ads_images
(
    id  serial PRIMARY KEY ,
    ads_id int,
    path TEXT,
    constraint fk_ads_id foreign key (ads_id) references ads (id)
);

ALTER TABLE ads DROP COLUMN image;
