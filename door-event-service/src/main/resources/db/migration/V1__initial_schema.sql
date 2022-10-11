create table door_event
(
    id         bigserial not null primary key,
    source_id  varchar(36) not null,
    ref_id     varchar(36),
    account_id bigint not null,
    key_id     bigint not null,
    door_id    bigint not null,
    ts         timestamp,
    type       varchar(10) not null
);