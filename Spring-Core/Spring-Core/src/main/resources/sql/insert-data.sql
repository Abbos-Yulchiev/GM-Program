insert  into users
        (id, username, email)
values  (1,'Soumitra_Roy','contact@roytuts.com'),
        (2,'Souvik_Sanyal','souvik.sanyal@email.com'),
        (3,'Souvik_Sanyals','souviks.sanyals@email.com');


insert into events
        (id, title, event_date)
values  (1, 'Halloween', '2022-10-03'),
        (2, 'Halloween', '2022-10-02'),
        (3, 'Halloween', '2022-10-01');


insert into ticket
        (id, userId, eventId, categories, place, sold)
values  (1, 1, 1, 'STANDARD', 'New York', true),
        (2, 2, 2, 'PREMIUM', 'Broykln', true),
        (15, 3, 3, 'BAR', 'Washington', true);

insert into event_ticket
    (id, eventId, ticket_amount, sold_tickets)
values  (1, 1, 100, 20),
        (2, 2, 200, 100),
        (3, 3, 150, 50);