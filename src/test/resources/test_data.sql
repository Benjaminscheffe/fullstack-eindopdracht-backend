-- add available roles
insert into roles(rolename) values ('ROLE_USER'), ('ROLE_ADMIN');

-- application data filling
insert into "USERS"(email, password, username) values
     ('henk@hotmail.com', '1234', 'henk');

insert into images(file_name) values
    ('test-track-image.jpg');

insert into beatfiles(file_name) values
    ('test-track.mp3');


insert into beats(bpm, price, user_id, title, beat_file_file_name, image_file_name) values
    (96, 10,1, 'Beat 1', 'test-track.mp3', 'test-track-image.jpg');





