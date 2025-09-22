-- add available roles
insert into roles(rolename) values ('ROLE_USER'), ('ROLE_ADMIN');

-- application data filling ww = 1234
insert into users(email, password, username) values
     ('dre@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'Dre Dre'),
     ('timbaland@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'Timbaland'),
     ('scott_s@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'Scott Storch'),
     ('dj_premier@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'DJ Premier');

insert into users_roles (users_id, roles_rolename) values
    (1, 'ROLE_USER'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_USER'),
    (4, 'ROLE_USER'),
    (1, 'ROLE_ADMIN');

insert into images(file_name) values
    ('test-track-image.jpg'),
    ('test-track-image2.jpg'),
    ('test-track-image3.jpg'),
    ('test-track-image4.jpg'),
    ('test-track-image5.jpg'),
    ('test-track-image6.jpg'),
    ('test-track-image7.jpg');

insert into beatfiles(file_name) values
    ('test-track.mp3'),
    ('test-track2.mp3'),
    ('test-track3.mp3'),
    ('test-track4.mp3'),
    ('test-track5.mp3'),
    ('test-track6.mp3'),
    ('test-track7.mp3');


insert into beats(bpm, price, user_id, title, beat_file_file_name, image_file_name) values
    (96, 10,1, 'Beat 1', 'test-track.mp3', 'test-track-image.jpg'),
    (70, 9,1, 'Beat 2', 'test-track2.mp3', 'test-track-image2.jpg'),
    (98, 10,2, 'Beat 3', 'test-track3.mp3', 'test-track-image3.jpg'),
    (100, 8,3, 'Beat 4', 'test-track4.mp3', 'test-track-image4.jpg'),
    (80, 8,3, 'Beat 5', 'test-track5.mp3', 'test-track-image5.jpg'),
    (110, 10,3, 'Beat 6', 'test-track6.mp3', 'test-track-image6.jpg'),
    (120, 11,3, 'Beat 7', 'test-track7.mp3', 'test-track-image7.jpg');


insert into reviews(score, beat_id, user_id, comment) values
    (7, 2, 4, 'mooi hoor'),
    (5, 2, 3, 'redelijk'),
    (9, 3, 2, 'heel mooi hoor'),
    (10, 6, 1, 'Beste!');


insert into orders(id, order_date, beat_id, user_id) values
    (nextval('order_number_seq'),'2007-12-03 10:15:30', 1,1),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',2, 1),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',3, 1),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',2, 4),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',3, 4);


