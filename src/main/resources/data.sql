-- add available roles
insert into roles(rolename) values ('ROLE_USER'), ('ROLE_ADMIN');

-- application data filling ww = 1234 for all users
insert into users(email, password, username) values
     ('dre@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'Dr-Dre'),
     ('timbaland@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'Timbaland'),
     ('scott_s@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'Scott-Storch'),
     ('dj_premier@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'DJ-Premier'),
     ('eminem@hotmail.com', '$2a$12$wdbJFmo39J4304gC/izhhOkac1rIa8tZXGdPQXcCku7eLZRlF6NT2', 'Eminem');

insert into users_roles (users_id, roles_rolename) values
    (1, 'ROLE_USER'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_USER'),
    (4, 'ROLE_USER'),
    (5, 'ROLE_USER'),
    (1, 'ROLE_ADMIN');

insert into images(file_name) values
    ('xxplosive.jpg'),
    ('break-ya-neck.jpg'),
    ('get-ur-freak-on.jpg'),
    ('still-dre.jpg'),
    ('mc-act.jpg'),
    ('when-the-music-stops.jpg'),
    ('livin-proof.jpg');

insert into beatfiles(file_name) values
    ('xxplosive.mp3'),
    ('break-ya-neck.mp3'),
    ('get-ur-freak-on.mp3'),
    ('still-dre.mp3'),
    ('mc-act.mp3'),
    ('when-the-music-stops.mp3'),
    ('livin-proof.mp3');

insert into beats(bpm, price, user_id, title, beat_file_file_name, image_file_name) values
    (96, 10,1, 'Xxplosive', 'xxplosive.mp3', 'xxplosive.jpg'),
    (70, 9,1, 'Break Ya Neck', 'break-ya-neck.mp3', 'break-ya-neck.jpg'),
    (98, 10,2, 'Get Ur Freak On', 'get-ur-freak-on.mp3', 'get-ur-freak-on.jpg'),
    (100, 8,3, 'Still Dre', 'still-dre.mp3', 'still-dre.jpg'),
    (80, 8,4, 'MC''s Act Like They Don''t Know', 'mc-act.mp3', 'mc-act.jpg'),
    (110, 10,5, 'When The Music Stops', 'when-the-music-stops.mp3', 'when-the-music-stops.jpg'),
    (120, 11,4, 'Livin'' Proof', 'livin-proof.mp3', 'livin-proof.jpg');

insert into reviews(score, beat_id, user_id, comment) values
    (7, 2, 4, 'Real neck breaker!!!'),
    (8, 2, 3, 'That''s some real Timbaland shit'),
    (9, 3, 2, 'Real classic West Coast G-Funk!! '),
    (8, 3, 5, 'West Coast Representing!! '),
    (9, 6, 1, 'Eminem behind the knobs, that''s the shit!');

insert into orders(id, order_date, beat_id, user_id) values
    (nextval('order_number_seq'),'2007-12-03 10:15:30', 4,1),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',3, 1),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',2, 2),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',2, 3),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',1, 4),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',2, 4),
    (nextval('order_number_seq'),'2007-12-03 10:15:30',3, 5);


