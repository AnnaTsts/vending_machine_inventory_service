alter table event drop constraint product_in_vent_ref;

alter table event drop constraint shelf_in_event;

alter table event drop constraint event_type_ref;

alter table product drop constraint brand_ref;

alter table product drop constraint product_type_ref;

alter table store drop constraint product_to_shelf_ref;

drop table brand;

drop table event;

drop table event_type;

drop table product;

drop table product_type;

drop table store;

/*==============================================================*/
/* Table: brand                                                 */
/*==============================================================*/
create table brand (
   brand_id             IDENTITY                 not null,
   brand_name           VARCHAR(128)         not null,
   constraint PK_BRAND primary key (brand_id)
);

/*==============================================================*/
/* Table: event                                                 */
/*==============================================================*/
create table event (
   event_id             IDENTITY                 not null,
   product_id           BIGINT                 null,
   shelf_id             BIGINT                 null,
   event_type_id        BIGINT                 null,
   event_time           DATE                 null,
   quantity             BIGINT                 null,
   constraint PK_EVENT primary key (event_id)
);

/*==============================================================*/
/* Table: event_type                                            */
/*==============================================================*/
create table event_type (
   event_type_id        IDENTITY                 not null,
   evet_type_name       VARCHAR(128)         not null,
   constraint PK_EVENT_TYPE primary key (event_type_id)
);

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product (
   product_id           IDENTITY                 not null,
   brand_id             BIGINT                 not null,
   product_type_id      BIGINT                 not null,
   product_name         VARCHAR(128)         not null,
   weight               DECIMAL              null,
   price                DECIMAL              not null,
   constraint PK_PRODUCT primary key (product_id)
);

/*==============================================================*/
/* Table: product_type                                          */
/*==============================================================*/
create table product_type (
   product_type_id      IDENTITY                 not null,
   product_type_name    VARCHAR(128)         not null,
   constraint PK_PRODUCT_TYPE primary key (product_type_id)
);

/*==============================================================*/
/* Table: store                                                 */
/*==============================================================*/
create table store (
   shelf_id             IDENTITY                 not null,
   product_id           BIGINT                 null,
   shelf_display_name   VARCHAR(16)          not null,
   quantity             BIGINT                 null,
   constraint PK_STORE primary key (shelf_id)
);

alter table event
   add constraint product_in_vent_ref foreign key (product_id)
      references product (product_id)
      on delete restrict on update restrict;

alter table event
   add constraint shelf_in_event foreign key (shelf_id)
      references store (shelf_id)
      on delete restrict on update restrict;

alter table event
   add constraint event_type_ref foreign key (event_type_id)
      references event_type (event_type_id)
      on delete restrict on update restrict;

alter table product
   add constraint brand_ref foreign key (brand_id)
      references brand (brand_id)
      on delete restrict on update restrict;

alter table product
   add constraint product_type_ref foreign key (product_type_id)
      references product_type (product_type_id)
      on delete restrict on update restrict;

alter table store
   add constraint product_to_shelf_ref foreign key (product_id)
      references product (product_id)

Insert into product_type (product_type_name) values ('water');
Insert into product_type (product_type_name) values ('tea');
Insert into product_type (product_type_name) values ('coke');
Insert into product_type (product_type_name) values ('pop');

INSERT into BRAND (BRAND_NAME) values ( 'Coca cola' );

insert into PRODUCT (brand_id, product_type_id, product_name, weight, price) values ( 1,3,'cola',0.5,10.0 )

insert into store(product_id,shelf_display_name,quantity) values