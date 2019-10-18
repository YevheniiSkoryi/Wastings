drop table if exists wasting;
drop table if exists moneyOnCurrentDay;

create table if not exists wasting
(
    id          int primary key not null,
    time_paying int             not null,
    value       int             not null,
    description text
);

create table  if not exists  moneyOnCurrentDay
(
    id           int primary key not null,
    date_of_month int             not null,
    value        int             not null
);