INSERT INTO MEMBER (name, email, password, role)
VALUES ('어드민', 'admin@email.com', 'password', 'ADMIN'),
       ('브라운', 'brown@email.com', 'brown', 'USER'),
       ('브리', 'brie@email.com', 'brie', 'USER'),
       ('솔라', 'solar@email.com', 'solar', 'USER');

INSERT INTO GAME_TIME (start_at)
VALUES ('10:00'),
       ('12:00'),
       ('14:00'),
       ('16:00'),
       ('18:00'),
       ('20:00');

INSERT INTO THEME (name, description, thumbnail)
VALUES ('레벨1 탈출', '우테코 레벨1을 탈출하는 내용입니다.', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('레벨2 탈출', '우테코 레벨2를 탈출하는 내용입니다.', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('레벨3 탈출', '우테코 레벨3을 탈출하는 내용입니다.', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('레벨4 탈출', '우테코 레벨4를 탈출하는 내용입니다.', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('레벨5 탈출', '우테코 레벨를 탈출하는 내용입니다.', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg');

INSERT INTO GAME_SCHEDULE (theme_id, date, time_id)
VALUES (1, '2025-07-07', 1),
       (2, '2025-07-08', 1),
       (2, '2025-07-09', 2),
       (3, '2025-07-11', 1),
       (3, '2025-07-11', 2),
       (3, '2025-07-12', 3),
       (4, '2025-07-12', 1),
       (4, '2025-07-12', 2),
       (4, '2025-07-12', 3),
       (4, '2025-07-12', 4),
       (5, '2025-07-12', 1),
       (5, '2025-07-12', 2),
       (5, '2025-07-12', 3);

INSERT INTO RESERVATION (member_id, schedule_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (3, 6),
       (3, 7),
       (3, 8),
       (3, 9),
       (4, 10),
       (4, 11),
       (4, 12),
       (4, 13);
