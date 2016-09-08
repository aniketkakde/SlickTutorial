# --- !Ups

create table "albums" (

  "id" INTEGER PRIMARY KEY  AUTOINCREMENT,
  "artist" varchar not null ,
  "title" varchar not null,
  "year" INTEGER not null,
  "rating" INTEGER not null
);


# --- !Downs

drop table "albums";