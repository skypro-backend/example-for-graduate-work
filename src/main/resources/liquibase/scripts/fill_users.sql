-- liquibase formatted sql

-- changeset DrogolovaNadezhda:fill_users-1
insert into user_table (email,firstname,image,lastname,"password",phone,"role")
values (
    'user@gmail.com',
    'Nadya',
    null,
    'Drogolova',
    'password',
    '615452155',
    null);