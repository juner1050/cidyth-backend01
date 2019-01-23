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
   ID                   int not null comment '编号',
   Application_User_Code int comment '申请人',
   Application_time     datetime comment '申请时间',
   User_Code            varchar(32) comment '获得勋章用户',
   Medal_Grade          int comment '勋章等级',
   Is_Approval          int comment '是否使用，0：使用；1：停用',
   Approval_User_Code   int comment '批准人',
   Approval_Time        datetime,
   Reason               varchar(1024) comment '否决原因',
   Case_Code            int,
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_Medal_Log comment '授勋历史';

/*==============================================================*/
/* Table: T_Medal_Type                                          */
/*==============================================================*/
create table T_Medal_Type
(
   ID                   int not null comment '编号',
   Grade                int comment '勋章等级',
   Medal_Name           varchar(32) comment '勋章名称',
   Medal_Relation_Score int comment '勋章对应的分数',
   Medal_IMG            longblob comment '勋章图片',
   Flag                 int comment '是否使用，0：使用；1：停用',
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_Medal_Type comment '勋章类型表';

/*==============================================================*/
/* Table: T_RealTime_User_Score                                 */
/*==============================================================*/
create table T_RealTime_User_Score
(
   ID                   int not null comment '编号',
   User_ID              varchar(32),
   Score                varchar(1024) comment '否决原因',
   Grade                int,
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_RealTime_User_Score comment '个人实时积分分数（按年度计算）';

/*==============================================================*/
/* Table: T_Score_Log                                           */
/*==============================================================*/
create table T_Score_Log
(
   ID                   int not null comment '编号',
   User_code            varchar(32),
   Input_Time           datetime,
   Score_Code           varchar(32) comment '获得勋章用户',
   Operation_Code       int comment '勋章等级',
   Operation_Type_code  int comment '批准人',
   Comment              varchar(1024),
   primary key (ID)
);

alter table T_Score_Log comment '积分类型';

/*==============================================================*/
/* Table: T_Score_Type                                          */
/*==============================================================*/
create table T_Score_Type
(
   ID                   int not null comment '编号',
   Score_Code           varchar(32) comment '获得勋章用户',
   User_Type_Code       int comment '勋章等级',
   Score_Type_code      int comment '批准人',
   Scord_Type_Name      datetime,
   Score                int comment '否决原因',
   Content              varchar(1024),
   primary key (ID)
);

alter table T_Score_Type comment '积分类型';

