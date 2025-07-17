-- add available roles
insert into roles(rolename) values ('ROLE_USER'), ('ROLE_ADMIN');

-- application data filling
insert into users(email, password, username) values
     ('henk@hotmail.com', '1234', 'henk'),
     ('klaas@hotmail.com', '1234', 'klaas'),
     ('piet@hotmail.com', '1234', 'piet'),
     ('puk@hotmail.com', '1234', 'puk');

insert into images(name) values
     ('image 1'),
     ('image 2'),
     ('image 3'),
     ('image 4'),
     ('image 5'),
     ('image 6'),
     ('image 7');

insert into beats(bpm, price, image_id, user_id, title) values
    (96, 10, 1,1, 'Beat 1'),
    (70, 9, 2,1, 'Beat 2'),
    (98, 10, 3,2, 'Beat 3'),
    (100, 8, 4,3, 'Beat 4'),
    (80, 8, 5,3, 'Beat 5'),
    (110, 10, 6,3, 'Beat 6'),
    (120, 11, 7,3, 'Beat 7');

insert into reviews(score, beat_id, user_id, comment) values
    (7, 2, 4, 'mooi hoor'),
    (5, 2, 4, 'redelijk'),
    (9, 3, 2, 'heel mooi hoor'),
    (10, 6, 1, 'Beste!');

insert into orders(id, order_date, beat_id, user_id) values
    (nextval('order_number_seq'),'2007-12-03 10:15:30', 1,4),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',2, 4),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',3, 4);






-- insert into users(username, password) values ('klaas', '$2a$12$kGrAOI4f0AYA.YXUAinxju6N6r4bCm5xEIAwPm/Q/xbyOOUfiiW/W');
-- insert into users_roles(roles_rolename, users_username) values ('ROLE_USER', 'klaas'), ('ROLE_ADMIN', 'klaas');