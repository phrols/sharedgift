--liquibase formatted sql
			
--changeset Philippe:1
create table family (
	id bigserial primary key not null,
	name varchar(250) not null
);