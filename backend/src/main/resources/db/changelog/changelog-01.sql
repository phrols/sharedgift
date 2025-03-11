--liquibase formatted sql
			
--changeset Philippe:1
create table family (
	id bigserial primary key not null,
	name text not null
);

--changeset Philippe:2
create table player (
    id bigserial primary key not null,
    name text not null,
    email text not null,
    family_id bigint not null references family(id)
);

create type event_status AS ENUM ('NEW', 'DONE', 'CANCELLED');

create table event (
    id bigserial primary key not null,
    name text not null,
    event_date date not null,
    family_id bigint not null references family,
    status event_status not null
);

create table assignment (
    id bigserial primary key not null,
    from_player bigint not null references player(id),
    to_player bigint not null references player(id),
    event_id bigint not null references event(id)
);

create table family_admin (
	id bigserial primary key not null,
    name text not null,
    email text not null,
    password text not null
);

alter table family add column admin_id bigint not null references family_admin(id);
