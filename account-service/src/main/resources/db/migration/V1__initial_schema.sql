create table account
(
    id      bigserial not null primary key,
    name    varchar(50) not null,
    address varchar(255) not null,
    phone   varchar(12) not null,
    key_group_id bigint
);

create type key_type as enum ('app', 'dongle');
create table door_key
(
    id   bigserial not null primary key,
    type key_type not null
);

create table door
(
    id       bigserial not null primary key,
	location varchar(255) not null
);

--one group - many keys
create table key_group
(
    id     bigint not null,
	key_id bigint not null references door_key (id),
	primary key(id, key_id)
);

--one group - many doors
create table door_group
(
    id      bigint not null,
	door_id bigint not null references door (id),
	primary key(id, door_id)
);

--one key group - many door group
create table key_group_door_group
(
    key_group_id  bigint not null,
    door_group_id bigint not null,
    primary key(key_group_id, door_group_id)
);

create type event_type as enum ('open', 'opened', 'failed');
create table door_event
(
    id   bigserial not null primary key,
    ref_id bigint not null,
    account_id bigint not null references account (id),
    key_id bigint not null references door_key (id),
    door_id bigint not null references door (id),
    ts timestamp,
    type event_type not null
);
--Master data
--account
insert into account(name, address, phone, key_group_id) values ('name1', 'address1', 'phone1', '1');
insert into account(name, address, phone, key_group_id) values ('name2', 'address2', 'phone2', '2');
--door_key
insert into door_key(type) values ('app');
insert into door_key(type) values ('dongle');
insert into door_key(type) values ('app');
insert into door_key(type) values ('dongle');
--door
insert into door(location) values ('door1');
insert into door(location) values ('door2');
insert into door(location) values ('door3');
insert into door(location) values ('door4');
insert into door(location) values ('door5');
insert into door(location) values ('door6');
--key_group
insert into key_group(id, key_id) values ('1','1');
insert into key_group(id, key_id) values ('1','2');
insert into key_group(id, key_id) values ('2','3');
insert into key_group(id, key_id) values ('2','4');
--door_group
insert into door_group(id, door_id) values ('1','1');
insert into door_group(id, door_id) values ('1','2');
insert into door_group(id, door_id) values ('2','3');
insert into door_group(id, door_id) values ('2','4');
insert into door_group(id, door_id) values ('3','5');
insert into door_group(id, door_id) values ('3','6');
--key_group_door_group
insert into key_group_door_group(key_group_id, door_group_id) values ('1','1');
insert into key_group_door_group(key_group_id, door_group_id) values ('1','2');
insert into key_group_door_group(key_group_id, door_group_id) values ('2','1');
insert into key_group_door_group(key_group_id, door_group_id) values ('2','2');
insert into key_group_door_group(key_group_id, door_group_id) values ('2','3');
