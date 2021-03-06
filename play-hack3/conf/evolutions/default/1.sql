# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table map (
  id                            integer auto_increment not null,
  latitude                      double,
  longitude                     double,
  percentage                    float,
  type                          varchar(7),
  constraint ck_map_type check ( type in ('credito','seguro')),
  constraint pk_map primary key (id)
);

create table task (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  done                          tinyint(1) default 0 not null,
  due_date                      datetime(6),
  constraint pk_task primary key (id)
);

create table twit (
  id                            integer auto_increment not null,
  text                          varchar(255),
  keywords                      varchar(255),
  constraint pk_twit primary key (id)
);


# --- !Downs

drop table if exists map;

drop table if exists task;

drop table if exists twit;

