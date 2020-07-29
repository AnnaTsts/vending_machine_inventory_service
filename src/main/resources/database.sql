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
   brand_id             INT4                 not null,
   brand_name           VARCHAR(128)         not null,
   constraint PK_BRAND primary key (brand_id)
);

/*==============================================================*/
/* Table: event                                                 */
/*==============================================================*/
create table event (
   event_id             INT4                 not null,
   product_id           INT4                 null,
   shelf_id             INT4                 null,
   event_type_id        INT4                 null,
   event_time           DATE                 null,
   quantity             INT4                 null,
   constraint PK_EVENT primary key (event_id)
);

/*==============================================================*/
/* Table: event_type                                            */
/*==============================================================*/
create table event_type (
   event_type_id        INT4                 not null,
   evet_type_name       VARCHAR(128)         not null,
   constraint PK_EVENT_TYPE primary key (event_type_id)
);

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product (
   product_id           INT4                 not null,
   brand_id             INT4                 not null,
   product_type_id      INT4                 not null,
   product_name         VARCHAR(128)         not null,
   weight               DECIMAL              null,
   price                DECIMAL              not null,
   constraint PK_PRODUCT primary key (product_id)
);

/*==============================================================*/
/* Table: product_type                                          */
/*==============================================================*/
create table product_type (
   product_type_id      INT4                 not null,
   product_type_name    VARCHAR(128)         not null,
   constraint PK_PRODUCT_TYPE primary key (product_type_id)
);

/*==============================================================*/
/* Table: store                                                 */
/*==============================================================*/
create table store (
   shelf_id             INT4                 not null,
   product_id           INT4                 null,
   shelf_display_name   VARCHAR(16)          not null,
   quantity             INT4                 null,
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