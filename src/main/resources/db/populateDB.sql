DELETE FROM admin_roles;
DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM users;
DELETE FROM admins;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO admins (name, email, password, role)
VALUES ('Admin0', 'admin0@yandex.ru', '{noop}password0', 'ADMIN'),
       ('Admin1', 'admin1@yandex.ru', '{noop}password1', 'ADMIN');

INSERT INTO users (name, email, password, role)
VALUES ('User0', 'user0@yandex.ru', '{noop}password0', 'USER'),
       ('User1', 'user1@gmail.com', '{noop}password1', 'USER'),
       ('User2', 'user2@gmail.com', '{noop}password2', 'USER'),
       ('User3', 'user3@gmail.com', '{noop}password3', 'USER'),
       ('User4', 'user4@gmail.com', '{noop}password4', 'USER');

INSERT INTO admin_roles (role, admin_id)
VALUES ('ADMIN', 100000),
       ('ADMIN', 100001);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100002),
       ('USER', 100003),
       ('USER', 100004),
       ('USER', 100005),
       ('USER', 100006);

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

