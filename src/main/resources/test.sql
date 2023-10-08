insert into images(image_name)
values ('weq');

insert into users (last_Name, first_Name, email, phone, image, username, password, user_image_id)
values ('Ivan', 'Ivan', 'ivan@mail.ru', 'phone','image', 'ivan', '$2a$10$oQ5so8XshqEoyemOczQW2.yuateW7VDQcNJHlIPSw8FQiVP5sQhOq', 1);

insert into ad(id, author, description, image, phone, price, title )
values (2, 1,'qwe','image','phone', 1, 'title');

insert into comment(text, created_at, user_id, ad_id)
values ('test_text','2023-10-08 09:49:39.000000', 2, 1);






