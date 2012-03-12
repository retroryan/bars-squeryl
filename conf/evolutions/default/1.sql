# --- First database schema

# --- !Ups

create table "BAR" (
  id                        SERIAL PRIMARY KEY,
  name                      varchar(255) not null
);


CREATE SEQUENCE "s_BAR_id"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

# --- !Downs

drop table if exists "BAR";
DROP SEQUENCE if exists "s_BAR_id";

