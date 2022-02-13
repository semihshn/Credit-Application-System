insert into users
    (id, first_name, last_name, identification_number, monthly_income, created_date, status)
values
       (2001, 'test first name', 'test last name', '12345678524', 1500, now(), 'ACTIVE'),
       (2002, 'test first name 2', 'test last name 2', '11111111111', 3000, now(), 'ACTIVE');

insert into credit_application_informations
    (id, credit_limit, credit_acceptance_status, created_date,user_id)
values (1001, 300000, true, now(),2001),
       (1002, 120000, true, now(),2002),
       (1003, 900000, true, now(),2001),
       (1004, 300200, true, now(),2002);