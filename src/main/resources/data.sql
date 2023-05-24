INSERT INTO transport_company (created_at, updated_at, is_deleted, company_name)
VALUES ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'company_name1'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'company_name2'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'company_name3'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'company_name4'),
 ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'company_name5');


INSERT INTO delivery_driver (created_at, updated_at, is_deleted, name, salary, is_penalty_deducted, transport_company_id)
VALUES ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'driver_name1', 10000, false, 1),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'driver_name2', 20000, false,1),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'driver_name3',30000, false,1),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'driver_name4',40000, true,2),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'driver_name5',50000, true,2);


INSERT INTO client_company (created_at, updated_at, is_deleted, company_name, contacts, description, compensation_payment, is_compensation_paid)
VALUES ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'client_company_name1', '010-1234-1234', 'company_description1', 1000, true),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'client_company_name2', '010-1234-1234', 'company_description2',1000, true),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'client_company_name3','010-1234-1234', 'company_description3',1000, true),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'client_company_name4','010-1234-1234', 'company_description4',1000,false),
       ('2023-01-01 00:00:00', '2023-01-01 00:00:00', false, 'dclient_company_name5','010-1234-1234', 'company_description5',1000, false);

