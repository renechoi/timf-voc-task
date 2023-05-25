INSERT INTO transport_company (created_at, updated_at, is_deleted, company_name)
VALUES ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 회사 1'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 회사 2'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 회사 3'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 회사 4'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 회사 5');


INSERT INTO delivery_driver (created_at, updated_at, is_deleted, name, salary, is_penalty_deducted, transport_company_id)
VALUES ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 기사 1', 10000, false, 1),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 기사 2', 20000, false,1),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 기사 3',30000, false,1),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 기사 4',40000, true,2),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '운송 기사 5',50000, true,2);


INSERT INTO client_company (created_at, updated_at, is_deleted, company_name, contacts, description, compensation_payment, is_compensation_paid)
VALUES ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '고객사 1', '010-1234-1234', 'company_description1', 1000, true),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '고객사 2', '010-1234-1234', 'company_description2',1000, true),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '고객사 3','010-1234-1234', 'company_description3',1000, true),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '고객사 4','010-1234-1234', 'company_description4',1000,false),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '고객사 5','010-1234-1234', 'company_description5',1000, false);


insert into claim(created_at, updated_at, is_deleted, person_name, content, handled)
values ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '클레임 요청인 1', '클레임 내용 1', false),
    ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '클레임 요청인 2', '클레임 내용 2', false),
    ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '클레임 요청인 3', '클레임 내용 3', false),
    ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '클레임 요청인 4', '클레임 내용 4', false),
    ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, '클레임 요청인 5', '클레임 내용 5', false);