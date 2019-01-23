/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/8 16:25:32                            */
/*==============================================================*/


drop table if exists T_Medal_Log;

drop table if exists T_Medal_Type;

drop table if exists T_RealTime_User_Score;

drop table if exists T_Score_Log;

drop table if exists T_Score_Type;

/*==============================================================*/
/* Table: T_Medal_Log                                           */
/*==============================================================*/
create table T_Medal_Log
(
   ID                   int not null comment '���',
   Application_User_Code int comment '������',
   Application_time     datetime comment '����ʱ��',
   User_Code            varchar(32) comment '���ѫ���û�',
   Medal_Grade          int comment 'ѫ�µȼ�',
   Is_Approval          int comment '�Ƿ�ʹ�ã�0��ʹ�ã�1��ͣ��',
   Approval_User_Code   int comment '��׼��',
   Approval_Time        datetime,
   Reason               varchar(1024) comment '���ԭ��',
   Case_Code            int,
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_Medal_Log comment '��ѫ��ʷ';

/*==============================================================*/
/* Table: T_Medal_Type                                          */
/*==============================================================*/
create table T_Medal_Type
(
   ID                   int not null comment '���',
   Grade                int comment 'ѫ�µȼ�',
   Medal_Name           varchar(32) comment 'ѫ������',
   Medal_Relation_Score int comment 'ѫ�¶�Ӧ�ķ���',
   Medal_IMG            longblob comment 'ѫ��ͼƬ',
   Flag                 int comment '�Ƿ�ʹ�ã�0��ʹ�ã�1��ͣ��',
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_Medal_Type comment 'ѫ�����ͱ�';

/*==============================================================*/
/* Table: T_RealTime_User_Score                                 */
/*==============================================================*/
create table T_RealTime_User_Score
(
   ID                   int not null comment '���',
   User_ID              varchar(32),
   Score                varchar(1024) comment '���ԭ��',
   Grade                int,
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_RealTime_User_Score comment '����ʵʱ���ַ���������ȼ��㣩';

/*==============================================================*/
/* Table: T_Score_Log                                           */
/*==============================================================*/
create table T_Score_Log
(
   ID                   int not null comment '���',
   User_code            varchar(32),
   Input_Time           datetime,
   Score_Code           varchar(32) comment '���ѫ���û�',
   Operation_Code       int comment 'ѫ�µȼ�',
   Operation_Type_code  int comment '��׼��',
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_Score_Log comment '��������';

/*==============================================================*/
/* Table: T_Score_Type                                          */
/*==============================================================*/
create table T_Score_Type
(
   ID                   int not null comment '���',
   Score_Code           varchar(32) comment '���ѫ���û�',
   User_Type_Code       int comment 'ѫ�µȼ�',
   Score_Type_code      int comment '��׼��',
   Scord_Type_Name      datetime,
   Score                int comment '���ԭ��',
   Content              varchar(1024),
   primary key (ID)
);

alter table T_Score_Type comment '��������';

