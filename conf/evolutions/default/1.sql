# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table basket (
  id                            bigint not null,
  user_email                    varchar(255),
  constraint uq_basket_user_email unique (user_email),
  constraint pk_basket primary key (id)
);
create sequence basket_seq;

create table category (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_category primary key (id)
);
create sequence category_seq;

create table category_product (
  category_id                   bigint not null,
  product_id                    bigint not null,
  constraint pk_category_product primary key (category_id,product_id)
);

create table chart (
  id                            bigint not null,
  song                          varchar(255),
  artist                        varchar(255),
  release                       varchar(255),
  constraint pk_chart primary key (id)
);
create sequence chart_seq;

create table order_item (
  id                            bigint not null,
  order_id                      bigint,
  basket_id                     bigint,
  product_id                    bigint,
  quantity                      integer,
  price                         double,
  constraint pk_order_item primary key (id)
);
create sequence order_item_seq;

create table product (
  id                            bigint not null,
  name                          varchar(255),
  description                   varchar(255),
  stock                         integer,
  price                         double,
  constraint pk_product primary key (id)
);
create sequence product_seq;

create table shop_order (
  id                            bigint not null,
  order_date                    timestamp,
  user_email                    varchar(255),
  constraint pk_shop_order primary key (id)
);
create sequence shop_order_seq;

create table user (
  role                          varchar(31) not null,
  email                         varchar(255) not null,
  name                          varchar(255),
  address                       varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (email)
);

alter table basket add constraint fk_basket_user_email foreign key (user_email) references user (email) on delete restrict on update restrict;

alter table category_product add constraint fk_category_product_category foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_category_product_category on category_product (category_id);

alter table category_product add constraint fk_category_product_product foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_category_product_product on category_product (product_id);

alter table order_item add constraint fk_order_item_order_id foreign key (order_id) references shop_order (id) on delete restrict on update restrict;
create index ix_order_item_order_id on order_item (order_id);

alter table order_item add constraint fk_order_item_basket_id foreign key (basket_id) references basket (id) on delete restrict on update restrict;
create index ix_order_item_basket_id on order_item (basket_id);

alter table order_item add constraint fk_order_item_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_order_item_product_id on order_item (product_id);

alter table shop_order add constraint fk_shop_order_user_email foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_shop_order_user_email on shop_order (user_email);


# --- !Downs

alter table basket drop constraint if exists fk_basket_user_email;

alter table category_product drop constraint if exists fk_category_product_category;
drop index if exists ix_category_product_category;

alter table category_product drop constraint if exists fk_category_product_product;
drop index if exists ix_category_product_product;

alter table order_item drop constraint if exists fk_order_item_order_id;
drop index if exists ix_order_item_order_id;

alter table order_item drop constraint if exists fk_order_item_basket_id;
drop index if exists ix_order_item_basket_id;

alter table order_item drop constraint if exists fk_order_item_product_id;
drop index if exists ix_order_item_product_id;

alter table shop_order drop constraint if exists fk_shop_order_user_email;
drop index if exists ix_shop_order_user_email;

drop table if exists basket;
drop sequence if exists basket_seq;

drop table if exists category;
drop sequence if exists category_seq;

drop table if exists category_product;

drop table if exists chart;
drop sequence if exists chart_seq;

drop table if exists order_item;
drop sequence if exists order_item_seq;

drop table if exists product;
drop sequence if exists product_seq;

drop table if exists shop_order;
drop sequence if exists shop_order_seq;

drop table if exists user;

