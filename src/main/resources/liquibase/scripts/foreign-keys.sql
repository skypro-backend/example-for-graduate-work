-- liquibase formatted sql

-- changeset yuzu:1

alter table if exists ads add constraint fk_ads_users foreign key (author_id) references users;
alter table if exists ads add constraint fk_ads_images foreign key (image_id) references images;
alter table if exists comments add constraint fk_comments_ads foreign key (ad_id) references ads;
alter table if exists comments add constraint fk_comments_users foreign key (author_id) references users;
alter table if exists users add constraint fk_users_images foreign key (image_id) references images;