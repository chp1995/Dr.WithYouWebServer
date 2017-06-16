/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/24 15:37:59                           */
/*==============================================================*/


drop table if exists DoctorPatient;

drop table if exists TempletCheckItem;

drop table if exists UserCheckItem;

drop table if exists UserTemplet;

drop table if exists checkeditem;

drop table if exists checkitem;

drop table if exists message;

drop table if exists templet;

drop table if exists user;

drop table if exists userinfo;

/*==============================================================*/
/* Table: DoctorPatient                                         */
/*==============================================================*/
create table DoctorPatient
(
   doctorusr            varchar(32) not null,
   patientusr           varchar(32) not null,
   primary key (doctorusr, patientusr)
);

/*==============================================================*/
/* Table: TempletCheckItem                                      */
/*==============================================================*/
create table TempletCheckItem
(
   cid                  int(10) not null,
   tid                  int(10) not null,
   primary key (cid, tid)
);

/*==============================================================*/
/* Table: UserCheckItem                                         */
/*==============================================================*/
create table UserCheckItem
(
   username             varchar(32) not null,
   id                   int(10) not null,
   primary key (username, id)
);

/*==============================================================*/
/* Table: UserTemplet                                           */
/*==============================================================*/
create table UserTemplet
(
   username             varchar(32) not null,
   tid                  int(10) not null,
   primary key (username, tid)
);

/*==============================================================*/
/* Table: checkeditem                                           */
/*==============================================================*/
create table checkeditem
(
   username             varchar(32),
   id                   int(10) not null,
   submittime           varchar(32),
   value                float
);

/*==============================================================*/
/* Table: checkitem                                             */
/*==============================================================*/
create table checkitem
(
   itemname             varchar(32) not null,
   checktime            varchar(32) not null,
   creator              varchar(32) not null,
   description          varchar(1024),
   cid                  int(10) not null auto_increment,
   primary key (cid)
);

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   sender               varchar(32),
   receiver             varchar(32),
   msg                  varchar(512) not null,
   time                 varchar(32) not null
);

/*==============================================================*/
/* Table: templet                                               */
/*==============================================================*/
create table templet
(
   templetname          varchar(32) not null,
   username             varchar(32),
   suitable             varchar(32) not null,
   description          varchar(1024),
   tid                  int(10) not null auto_increment,
   primary key (tid)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   username             varchar(32) not null,
   password             varchar(32) not null,
   bedoctor             bool not null,
   token                varchar(36),
   primary key (username)
);

/*==============================================================*/
/* Table: userinfo                                              */
/*==============================================================*/
create table userinfo
(
   username             varchar(32),
   name                 varchar(32),
   phone                varchar(11),
   illness              varchar(32),
   begintime            varchar(32),
   endtime              varchar(32),
   sex                  varchar(10),
   iconpath             varchar(64),
   idcard               varchar(18)
);

alter table DoctorPatient add constraint FK_Relationship_10 foreign key (doctorusr)
      references user (username) on delete restrict on update restrict;

alter table DoctorPatient add constraint FK_Relationship_11 foreign key (patientusr)
      references user (username) on delete restrict on update restrict;

alter table TempletCheckItem add constraint FK_Relationship_5 foreign key (cid)
      references checkitem (cid) on delete restrict on update restrict;

alter table TempletCheckItem add constraint FK_Relationship_6 foreign key (tid)
      references templet (tid) on delete restrict on update restrict;

alter table UserCheckItem add constraint FK_Relationship_2 foreign key (username)
      references user (username) on delete restrict on update restrict;

alter table UserCheckItem add constraint FK_Relationship_3 foreign key (id)
      references checkitem (cid) on delete restrict on update restrict;

alter table UserTemplet add constraint FK_Relationship_8 foreign key (username)
      references user (username) on delete restrict on update restrict;

alter table UserTemplet add constraint FK_Relationship_9 foreign key (tid)
      references templet (tid) on delete restrict on update restrict;

alter table checkeditem add constraint FK_Relationship_13 foreign key (username)
      references user (username) on delete restrict on update restrict;

alter table checkeditem add constraint FK_Relationship_4 foreign key (id)
      references checkitem (cid) on delete restrict on update restrict;

alter table checkitem add constraint FK_Relationship_12 foreign key (creator)
      references user (username) on delete restrict on update restrict;

alter table message add constraint FK_Relationship_14 foreign key (receiver)
      references user (username) on delete restrict on update restrict;

alter table message add constraint FK_Relationship_7 foreign key (sender)
      references user (username) on delete restrict on update restrict;

alter table templet add constraint FK_Relationship_15 foreign key (username)
      references user (username) on delete restrict on update restrict;

alter table userinfo add constraint FK_Relationship_1 foreign key (username)
      references user (username) on delete restrict on update restrict;

