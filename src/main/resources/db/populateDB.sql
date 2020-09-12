DELETE FROM admins;
DELETE FROM users;
DELETE FROM votes;
DELETE FROM dishes;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO admins (name, email, password)
VALUES ('Admin0', 'admin0@yandex.ru', 'password0'),
       ('Admin1', 'admin1@gmail.com', 'password1');

INSERT INTO users (name, email, password)
VALUES ('User0', 'user0@yandex.ru', 'password0'),
       ('User1', 'user1@gmail.com', 'password1'),
       ('User2', 'user2@gmail.com', 'password2'),
       ('User3', 'user3@gmail.com', 'password3'),
       ('User4', 'user4@gmail.com', 'password4');

INSERT INTO dishes (admin_id, name, price)
VALUES (100000, 'пирог', 150.56),
       (100000, 'десерт', 200.13),
       (100000, 'чай', 100.05),
       (100001, 'суп', 150.34),
       (100001, 'пельмени', 200.54),
       (100001, 'сок', 100.57);


INSERT INTO votes (admin_id, user_id, date, time)
VALUES (100000, 100002, '2020-08-25', '10:00:00'),
       (100000, 100003, '2020-08-25', '13:00:00'),
       (100000, 100004, '2020-08-25', '10:00:00'),
       (100001, 100005, '2020-08-25', '10:00:00'),
       (100001, 100006, '2020-08-25', '10:00:00');

