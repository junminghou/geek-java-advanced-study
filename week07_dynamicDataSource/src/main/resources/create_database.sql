drop schema if exists my_temp_database;
drop schema if exists my_temp_database2;

create schema my_temp_database collate utf8mb4_unicode_ci;
create schema my_temp_database2 collate utf8mb4_unicode_ci;

use my_temp_database;

create table my_temp_database.user
(
    id   bigint auto_increment
        primary key,
    code int          null,
    name varchar(200) null,
    age  int          null
)
    comment '用户表';

use my_temp_database2;
create table my_temp_database2.user
(
    id   bigint auto_increment
        primary key,
    code int          null,
    name varchar(200) null,
    age  int          null
)
    comment '用户表';

