create table users (
  id serial primary key,
  username varchar(30) not null,
  email varchar(100) unique not null
);

create table events (
  id BIGINT primary key,
  title varchar(100) not null,
  event_date date not null
);

create table ticket (
  id BIGINT primary key,
  userId serial,
  eventId BIGINT,
  categories varchar(30) not null,
  place varchar(50) not null,
  sold boolean
);

create table event_ticket (
  id BIGINT primary key,
  eventId SERIAL,
  ticket_amount INT,
  sold_tickets INT
);

