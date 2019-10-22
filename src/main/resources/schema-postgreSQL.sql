drop table if exists wasting;
drop table if exists moneyOnCurrentDay;
drop table if exists person;

create table if not exists person
(
    id text primary key
);

create table if not exists wasting
(
    id          int primary key not null,
    time_paying int             not null,
    user_id     int             not null,
    value       int             not null,
    description text,
    foreign key (user_id) references person (id)
);

create table if not exists moneyOnCurrentDay
(
    id            int primary key not null,
    date_of_month int             not null,
    value         int             not null,
    user_id       int             not null,
    foreign key (user_id) references person (id)
);

insert into person values ('yevhenii')