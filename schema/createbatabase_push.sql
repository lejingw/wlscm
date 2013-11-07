/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2012/8/1 10:40:03                            */
/*==============================================================*/


ALTER TABLE JAT_PL_DISPLAY_ORGTYPE_LINE
   DROP CONSTRAINT FK_JAT_PL_DISPLAY_ORGTYPE_LINE;

ALTER TABLE JAT_PL_DISPLAY_STANDAND
   DROP CONSTRAINT FK_JAT_PL_DISPLAY_STANDAND;

ALTER TABLE JAT_PL_GATHER_ORDER_LINE
   DROP CONSTRAINT FK_JAT_PL_GATHER_ORDER_LINE;

ALTER TABLE JAT_PL_GATHER_ORDER_LINE
   DROP CONSTRAINT FK_JAT_PL_GATHER_ORDER_LINE2;

ALTER TABLE JAT_PL_GATHER_SET_GRADE
   DROP CONSTRAINT FK_JAT_PL_GATHER_SET_GRADE;

ALTER TABLE JAT_PL_GATHER_SET_LINE
   DROP CONSTRAINT FK_JAT_PL_GATHER_SET_LINE;

ALTER TABLE JAT_PL_GATHER_SET_SIZE
   DROP CONSTRAINT FK_JAT_PL_GATHER_SET_SIZE;

ALTER TABLE JAT_PL_GATHER_SET_STYLE
   DROP CONSTRAINT FK_JAT_PL_GATHER_SET_SALE;

DROP view JAV_PL_SALE_STATISTICS;

DROP TABLE JAT_PL_BASIC_DISTRIBUTE_NUM CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_BASIC_EXCLUDE_ORNA CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_BASIC_GRADE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_BASIC_SALABLE_REATE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_BASIC_SIZE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_BASIC_TURNOVER_NUM CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_BASIC_WEAR_SIZE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_DISPLAY_ORGTYPE_HEAD CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_DISPLAY_ORGTYPE_LINE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_DISPLAY_STANDAND CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_GATHER_ORDER_LINE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_GATHER_SET_GRADE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_GATHER_SET_HEAD CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_GATHER_SET_LINE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_GATHER_SET_SIZE CASCADE CONSTRAINTS;

DROP TABLE JAT_PL_GATHER_SET_STYLE CASCADE CONSTRAINTS;

DROP INDEX JAT_PL_SALE_STATISTICS_IDX3;

DROP INDEX JAT_PL_SALE_STATISTICS_IDX2;

DROP INDEX JAT_PL_SALE_STATISTICS_IDX1;

DROP TABLE JAT_PL_SALE_STATISTICS_GRADE CASCADE CONSTRAINTS;

DROP SEQUENCE JAT_PL_BASIC_GRADE_SEQ;

DROP SEQUENCE JAT_PL_BASIC_SALABLE_REATE_SEQ;

DROP SEQUENCE JAT_PL_BASIC_SIZE_SEQ;

DROP SEQUENCE JAT_PL_BASIC_WEAR_SIZE_SEQ;

DROP SEQUENCE JAT_PL_DISPLAY_ORGTYPE_HSEQ;

DROP SEQUENCE JAT_PL_DISPLAY_ORGTYPE_LSEQ;

DROP SEQUENCE JAT_PL_DISPLAY_STANDAND_SEQ;

DROP SEQUENCE JAT_PL_DISTRIBUTE_NUM_SEQ;

DROP SEQUENCE JAT_PL_GATHER_ORDER_HEAD_SEQ;

DROP SEQUENCE JAT_PL_GATHER_ORDER_LINE_SEQ;

DROP SEQUENCE JAT_PL_GATHER_SET_GRADE_SEQ;

DROP SEQUENCE JAT_PL_GATHER_SET_HEAD_SEQ;

DROP SEQUENCE JAT_PL_GATHER_SET_LINE_SEQ;

DROP SEQUENCE JAT_PL_GATHER_SET_SIZE_SEQ;

DROP SEQUENCE JAT_PL_GATHER_SET_STYLE_SEQ;

DROP SEQUENCE JAT_PL_TURNOVER_NUM_SEQ;

DROP SEQUENCE JAT_SYS_EXCEL_TEMP_DATA_SEQ;

CREATE SEQUENCE JAT_PL_BASIC_GRADE_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_BASIC_SALABLE_REATE_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_BASIC_SIZE_SEQ
INCREMENT BY 1
START WITH 1
  MAXVALUE 99999999
  MINVALUE 1;

CREATE SEQUENCE JAT_PL_BASIC_WEAR_SIZE_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_DISPLAY_ORGTYPE_HSEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_DISPLAY_ORGTYPE_LSEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_DISPLAY_STANDAND_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_DISTRIBUTE_NUM_SEQ
INCREMENT BY 1
START WITH 1
  MAXVALUE 99999999
  MINVALUE 1;

CREATE SEQUENCE JAT_PL_GATHER_ORDER_HEAD_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_GATHER_ORDER_LINE_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_GATHER_SET_GRADE_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_GATHER_SET_HEAD_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_GATHER_SET_LINE_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_GATHER_SET_SIZE_SEQ;

CREATE SEQUENCE JAT_PL_GATHER_SET_STYLE_SEQ
INCREMENT BY 1
START WITH 1
 MINVALUE 1
 MAXVALUE 99999999;

CREATE SEQUENCE JAT_PL_TURNOVER_NUM_SEQ
INCREMENT BY 1
START WITH 1
  MAXVALUE 99999999
  MINVALUE 1;

CREATE SEQUENCE JAT_SYS_EXCEL_TEMP_DATA_SEQ
INCREMENT BY 1
START WITH 1
 MAXVALUE 99999999
 MINVALUE 1;

/*==============================================================*/
/* Table: JAT_PL_BASIC_DISTRIBUTE_NUM                           */
/*==============================================================*/
CREATE TABLE JAT_PL_BASIC_DISTRIBUTE_NUM  (
   BILLID               NUMBER(8)                       NOT NULL,
   ORG_ID               NUMBER(8),
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   ANALYSIS_ID          NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   DISTRIBUTE_NUM       NUMBER(8),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_BASIC_DISTRIBUTE_NUM PRIMARY KEY (BILLID)
);

COMMENT ON TABLE JAT_PL_BASIC_DISTRIBUTE_NUM IS
'铺货量设置';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.BILLID IS
'单据id';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.ORG_ID IS
'组织';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.DISTRIBUTE_NUM IS
'铺货量';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_BASIC_DISTRIBUTE_NUM.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_BASIC_EXCLUDE_ORNA                             */
/*==============================================================*/
CREATE TABLE JAT_PL_BASIC_EXCLUDE_ORNA  (
   ORNA_CODE            VARCHAR2(20)                    NOT NULL,
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_BASIC_EXCLUDE_ORNA PRIMARY KEY (ORNA_CODE)
);

COMMENT ON TABLE JAT_PL_BASIC_EXCLUDE_ORNA IS
'排除饰品';

COMMENT ON COLUMN JAT_PL_BASIC_EXCLUDE_ORNA.ORNA_CODE IS
'饰品编码';

COMMENT ON COLUMN JAT_PL_BASIC_EXCLUDE_ORNA.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_BASIC_EXCLUDE_ORNA.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_BASIC_EXCLUDE_ORNA.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_BASIC_EXCLUDE_ORNA.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_BASIC_EXCLUDE_ORNA.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_BASIC_GRADE                                    */
/*==============================================================*/
CREATE TABLE JAT_PL_BASIC_GRADE  (
   BILLID               NUMBER(8)                       NOT NULL,
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   ANALYSIS_ID          NUMBER(8),
   COLOR_GRADE_ID       NUMBER(8),
   CLEAN_ID             NUMBER(8),
   CLEAN_GRADE_ID       NUMBER(8),
   COLOR_GRADE_GRADE_ID NUMBER(8),
   RATE                 NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_BASIC_GRADE PRIMARY KEY (BILLID)
);

COMMENT ON TABLE JAT_PL_BASIC_GRADE IS
'下单品质';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.BILLID IS
'单据id';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.COLOR_GRADE_ID IS
'色级';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.CLEAN_ID IS
'净度';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.CLEAN_GRADE_ID IS
'净度品质';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.COLOR_GRADE_GRADE_ID IS
'色级品质';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.RATE IS
'比例';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_BASIC_GRADE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_BASIC_SALABLE_REATE                            */
/*==============================================================*/
CREATE TABLE JAT_PL_BASIC_SALABLE_REATE  (
   REGION_ID            NUMBER(8)                       NOT NULL,
   SALE_DIS_RATE        NUMBER(24,6),
   UNSALE_DIS_RATE      NUMBER(24,6),
   SALE_TURN_RATE       NUMBER(24,6),
   UNSALE_TURN_RATE     NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_BASIC_SALABLE_REATE PRIMARY KEY (REGION_ID)
);

COMMENT ON TABLE JAT_PL_BASIC_SALABLE_REATE IS
'区域畅销款比例';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.REGION_ID IS
'区域';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.SALE_DIS_RATE IS
'畅销款铺货比例';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.UNSALE_DIS_RATE IS
'非畅销款铺货比例';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.SALE_TURN_RATE IS
'畅销款周转比例';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.UNSALE_TURN_RATE IS
'非畅销周转比例';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_BASIC_SALABLE_REATE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_BASIC_SIZE                                     */
/*==============================================================*/
CREATE TABLE JAT_PL_BASIC_SIZE  (
   BILLID               NUMBER(8)                       NOT NULL,
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   SIZE_ID              NUMBER(8),
   START_SIZE_ID        NUMBER(8),
   END_SIZE_ID          NUMBER(8),
   RATE                 NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_BASIC_SIZE PRIMARY KEY (BILLID)
);

COMMENT ON TABLE JAT_PL_BASIC_SIZE IS
'下单尺寸';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.BILLID IS
'单据id';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.SIZE_ID IS
'尺寸名称';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.START_SIZE_ID IS
'尺寸起';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.END_SIZE_ID IS
'尺寸止';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.RATE IS
'比例';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_BASIC_SIZE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_BASIC_TURNOVER_NUM                             */
/*==============================================================*/
CREATE TABLE JAT_PL_BASIC_TURNOVER_NUM  (
   BILLID               NUMBER(8)                       NOT NULL,
   ORG_ID               NUMBER(8),
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   ANALYSIS_ID          NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   START_DATE           CHAR(10),
   END_DATE             CHAR(10),
   TURNOVER_NUM         NUMBER(8),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_BASIC_TURNOVER_NUM PRIMARY KEY (BILLID)
);

COMMENT ON TABLE JAT_PL_BASIC_TURNOVER_NUM IS
'周转量设置';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.BILLID IS
'单据id';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.ORG_ID IS
'组织';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.START_DATE IS
'周转开始日期';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.END_DATE IS
'周转结束日期';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.TURNOVER_NUM IS
'周转量';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_BASIC_TURNOVER_NUM.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_BASIC_WEAR_SIZE                                */
/*==============================================================*/
CREATE TABLE JAT_PL_BASIC_WEAR_SIZE  (
   BILLID               NUMBER(8)                       NOT NULL,
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   WEAR_ID              NUMBER(8),
   SIZE_ID              NUMBER(8),
   START_SIZE_ID        NUMBER(8),
   END_SIZE_ID          NUMBER(8),
   RATE                 NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_BASIC_WEAR_SIZE PRIMARY KEY (BILLID)
);

COMMENT ON TABLE JAT_PL_BASIC_WEAR_SIZE IS
'佩戴对像尺寸比例';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.BILLID IS
'单据id';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.WEAR_ID IS
'佩戴对象';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.SIZE_ID IS
'尺寸名称';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.START_SIZE_ID IS
'尺寸起';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.END_SIZE_ID IS
'尺寸止';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.RATE IS
'比例';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_BASIC_WEAR_SIZE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_DISPLAY_ORGTYPE_HEAD                           */
/*==============================================================*/
CREATE TABLE JAT_PL_DISPLAY_ORGTYPE_HEAD  (
   HEADID               NUMBER(8)                       NOT NULL,
   ORGTYPE_NAME         VARCHAR2(256),
   MEMO                 VARCHAR2(1024),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_DISPLAY_ORGTYPE_HEAD PRIMARY KEY (HEADID)
);

COMMENT ON TABLE JAT_PL_DISPLAY_ORGTYPE_HEAD IS
'畅销款陈列组织类别头表';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.HEADID IS
'单据ID';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.ORGTYPE_NAME IS
'类别名称';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.MEMO IS
'备注';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_HEAD.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_DISPLAY_ORGTYPE_LINE                           */
/*==============================================================*/
CREATE TABLE JAT_PL_DISPLAY_ORGTYPE_LINE  (
   LINEID               NUMBER(8)                       NOT NULL,
   HEADID               NUMBER(8),
   ORG_ID               NUMBER(8),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_DISPLAY_ORGTYPE_LINE PRIMARY KEY (LINEID)
);

COMMENT ON TABLE JAT_PL_DISPLAY_ORGTYPE_LINE IS
'畅销款陈列组织类别行表';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_LINE.LINEID IS
'行ID';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_LINE.HEADID IS
'头ID';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_LINE.ORG_ID IS
'组织';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_LINE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_LINE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_DISPLAY_ORGTYPE_LINE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_DISPLAY_STANDAND                               */
/*==============================================================*/
CREATE TABLE JAT_PL_DISPLAY_STANDAND  (
   LINEID               NUMBER(8)                       NOT NULL,
   HEADID               NUMBER(8),
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   ANALYSIS_ID          NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   STYLE_MIDDLE_CLASS_ID NUMBER(8),
   STYLE_ORNA_CLASS_ID  NUMBER(8),
   STYLE_ID             NUMBER(8),
   MIN_COUNT            NUMBER(24,6),
   MAX_COUNT            NUMBER(24,6),
   MEMO                 VARCHAR2(1024),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_DISPLAY_STANDAND PRIMARY KEY (LINEID)
);

COMMENT ON TABLE JAT_PL_DISPLAY_STANDAND IS
'畅销款陈列标准';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.LINEID IS
'行id';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.HEADID IS
'组织类别';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.STYLE_MIDDLE_CLASS_ID IS
'款式中类';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.STYLE_ORNA_CLASS_ID IS
'款式小类';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.STYLE_ID IS
'款式';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.MIN_COUNT IS
'最小量';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.MAX_COUNT IS
'最大量';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.MEMO IS
'备注';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_DISPLAY_STANDAND.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_GATHER_ORDER_LINE                              */
/*==============================================================*/
CREATE TABLE JAT_PL_GATHER_ORDER_LINE  (
   LINEID               NUMBER(8)                       NOT NULL,
   HEADID               NUMBER(8),
   SET_LINE_ID          NUMBER(8),
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   ANALYSIS_ID          NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   STYLE_MIDDLE_CLASS_ID NUMBER(8),
   STYLE_ORNA_CLASS_ID  NUMBER(8),
   STYLE_ID             NUMBER(8),
   QUALITY_ID           NUMBER(8),
   BRACKET_COLOR_ID     NUMBER(8),
   UNIT_ID              VARCHAR2(20),
   SIZE_ID              NUMBER(8),
   COLOR_GRADE_ID       NUMBER(8),
   CLEAN_ID             NUMBER(8),
   COLOR_GRADE_GRADE_ID VARCHAR2(20),
   CLEAN_GRADE_ID       VARCHAR2(20),
   ORIGNAL_ORDER_NUM    NUMBER(24,6),
   ORDER_NUM            NUMBER(24,6),
   SALABLE_FLAG         NUMBER(1),
   SET_STYLE_ID         NUMBER(8),
   SET_GRADE_ID         NUMBER(8),
   SET_SIZE_ID          NUMBER(8),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_GATHER_ORDER_LINE PRIMARY KEY (LINEID)
);

COMMENT ON TABLE JAT_PL_GATHER_ORDER_LINE IS
'推式总单行';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.LINEID IS
'行ID';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.HEADID IS
'头ID';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.SET_LINE_ID IS
'总单设置行ID';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.STYLE_MIDDLE_CLASS_ID IS
'款式中类';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.STYLE_ORNA_CLASS_ID IS
'款式小类';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.STYLE_ID IS
'款式';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.QUALITY_ID IS
'材质';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.BRACKET_COLOR_ID IS
'材质颜色';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.UNIT_ID IS
'计量单位';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.SIZE_ID IS
'尺寸';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.COLOR_GRADE_ID IS
'色级';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.CLEAN_ID IS
'净度';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.COLOR_GRADE_GRADE_ID IS
'色级品质';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.CLEAN_GRADE_ID IS
'净度品质';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.ORIGNAL_ORDER_NUM IS
'初始下单数量';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.ORDER_NUM IS
'下单数量';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.SALABLE_FLAG IS
'是否畅销款';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.SET_STYLE_ID IS
'设置款式行ID';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.SET_GRADE_ID IS
'设置品质行ID';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.SET_SIZE_ID IS
'设置尺寸行ID';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_GATHER_ORDER_LINE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_GATHER_SET_GRADE                               */
/*==============================================================*/
CREATE TABLE JAT_PL_GATHER_SET_GRADE  (
   LISTID               NUMBER(8)                       NOT NULL,
   LINEID               NUMBER(8),
   COLOR_GRADE_ID       NUMBER(8),
   CLEAN_ID             NUMBER(8),
   COLOR_GRADE_GRADE_ID VARCHAR2(20),
   CLEAN_GRADE_ID       VARCHAR2(20),
   RATE                 NUMBER(24,6),
   ORDER_NUM            NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_GATHER_SET_GRADE PRIMARY KEY (LISTID)
);

COMMENT ON TABLE JAT_PL_GATHER_SET_GRADE IS
'推式总单设置品质比例行';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.LISTID IS
'明细ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.LINEID IS
'行ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.COLOR_GRADE_ID IS
'色级';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.CLEAN_ID IS
'净度';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.COLOR_GRADE_GRADE_ID IS
'色级品质';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.CLEAN_GRADE_ID IS
'净度品质';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.RATE IS
'比例';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.ORDER_NUM IS
'数量';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_GATHER_SET_GRADE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_GATHER_SET_HEAD                                */
/*==============================================================*/
CREATE TABLE JAT_PL_GATHER_SET_HEAD  (
   HEADID               NUMBER(8)                       NOT NULL,
   BILL_NO              VARCHAR2(20),
   ORG_ID               NUMBER(8),
   REGION_ID            NUMBER(8),
   CYCLE_TYPE_ID        NUMBER(8),
   ARTICLE_TYPE_IDS     VARCHAR2(1024),
   PUR_DATE_START       VARCHAR2(10),
   PUR_DATE_END         VARCHAR2(10),
   SALE_DATE_START      VARCHAR2(10),
   SALE_DATE_END        VARCHAR2(10),
   PUR_ARRIVE_DATE_END  VARCHAR2(10),
   SALE_DIS_RATE        NUMBER(24,6),
   SALE_TURN_RATE       NUMBER(24,6),
   MEMO                 VARCHAR2(1024),
   DOTYPE               VARCHAR2(20),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   UPDATE_ID            NUMBER(8),
   UPDATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_GATHER_SET_HEAD PRIMARY KEY (HEADID)
);

COMMENT ON TABLE JAT_PL_GATHER_SET_HEAD IS
'推式总单设置头表';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.HEADID IS
'单据ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.BILL_NO IS
'单据编号';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.ORG_ID IS
'组织';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.REGION_ID IS
'区域';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.CYCLE_TYPE_ID IS
'周期类型';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.ARTICLE_TYPE_IDS IS
'商品类别';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.PUR_DATE_START IS
'购物开始日期';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.PUR_DATE_END IS
'购物结束日期';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.SALE_DATE_START IS
'销售开始日期';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.SALE_DATE_END IS
'销售结束日期';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.PUR_ARRIVE_DATE_END IS
'最后到货日期';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.SALE_DIS_RATE IS
'铺货畅销款比例%';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.SALE_TURN_RATE IS
'周转畅销款比例%';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.MEMO IS
'备注';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.DOTYPE IS
'执行功能';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.UPDATE_ID IS
'修改人';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.UPDATE_DATE IS
'修改时间';

COMMENT ON COLUMN JAT_PL_GATHER_SET_HEAD.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_GATHER_SET_LINE                                */
/*==============================================================*/
CREATE TABLE JAT_PL_GATHER_SET_LINE  (
   LINEID               NUMBER(8)                       NOT NULL,
   HEADID               NUMBER(8),
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   ANALYSIS_ID          NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   ORDER_NUM            NUMBER(24,6),
   SALABLE_NUM          NUMBER(24,6),
   UNSALABLE_NUM        NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_GATHER_SET_LINE PRIMARY KEY (LINEID)
);

COMMENT ON TABLE JAT_PL_GATHER_SET_LINE IS
'推式总单设置行表';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.LINEID IS
'行ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.HEADID IS
'头ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.ORDER_NUM IS
'数量';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.SALABLE_NUM IS
'畅销款数量';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.UNSALABLE_NUM IS
'非畅销款数量';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_GATHER_SET_LINE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_GATHER_SET_SIZE                                */
/*==============================================================*/
CREATE TABLE JAT_PL_GATHER_SET_SIZE  (
   LISTID               NUMBER(8)                       NOT NULL,
   LINEID               NUMBER(8),
   SIZE_ID              NUMBER(8),
   START_SIZE_ID        NUMBER(8),
   END_SIZE_ID          NUMBER(8),
   RATE                 NUMBER(24,6),
   ORDER_NUM            NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_GATHER_SET_SIZE PRIMARY KEY (LISTID)
);

COMMENT ON TABLE JAT_PL_GATHER_SET_SIZE IS
'推式总单设置尺寸比例行';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.LISTID IS
'明细ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.LINEID IS
'行ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.SIZE_ID IS
'尺寸名称';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.START_SIZE_ID IS
'尺寸起';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.END_SIZE_ID IS
'尺寸止';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.RATE IS
'比例';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.ORDER_NUM IS
'数量';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_GATHER_SET_SIZE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_GATHER_SET_STYLE                               */
/*==============================================================*/
CREATE TABLE JAT_PL_GATHER_SET_STYLE  (
   LISTID               NUMBER(8)                       NOT NULL,
   LINEID               NUMBER(8),
   SALABLE_FLAG         NUMBER(1),
   STYLE_ID             NUMBER(8),
   QUALITY_ID           NUMBER(8),
   BRACKET_COLOR_ID     NUMBER(8),
   RATE                 NUMBER(24,6),
   STOCK_NUM            NUMBER(24,6),
   HALF_YEAR_AVG_NUM    NUMBER(24,6),
   MONTH_NUM            NUMBER(24,6),
   ORDER_NUM            NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8),
   CONSTRAINT PK_JAT_PL_GATHER_SET_STYLE PRIMARY KEY (LISTID)
);

COMMENT ON TABLE JAT_PL_GATHER_SET_STYLE IS
'推式总单设置畅销款比例行';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.LISTID IS
'明细ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.LINEID IS
'行ID';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.SALABLE_FLAG IS
'是否畅销款';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.STYLE_ID IS
'款式';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.QUALITY_ID IS
'材质';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.BRACKET_COLOR_ID IS
'材质颜色';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.RATE IS
'比例';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.STOCK_NUM IS
'库存';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.HALF_YEAR_AVG_NUM IS
'半年平均销售';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.MONTH_NUM IS
'上月销售';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.ORDER_NUM IS
'数量';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_GATHER_SET_STYLE.STATUS IS
'状态';

/*==============================================================*/
/* Table: JAT_PL_SALE_STATISTICS_GRADE                          */
/*==============================================================*/
CREATE TABLE JAT_PL_SALE_STATISTICS_GRADE  (
   ORG_ID               NUMBER(8),
   ITEM_CLASS_ID        NUMBER(8),
   ORNA_CLASS_ID        NUMBER(8),
   ANALYSIS_ID          NUMBER(8),
   STYLE_ITEM_CLASS_ID  NUMBER(8),
   STYLE_MIDDLE_CLASS_ID NUMBER(8),
   STYLE_ORNA_CLASS_ID  NUMBER(8),
   STYLE_ID             NUMBER(8),
   COLOR_GRADE_ID       NUMBER(8),
   CLEAN_ID             NUMBER(8),
   SIZE_ID              NUMBER(8),
   STOCK_NUM            NUMBER(24,6),
   ONWAY_NUM            NUMBER(24,6),
   YEAR_NUM             NUMBER(24,6),
   HALF_YEAR_NUM        NUMBER(24,6),
   HALF_YEAR_AVG_NUM    NUMBER(24,6),
   MONTH_NUM            NUMBER(24,6),
   CREATE_ID            NUMBER(8),
   CREATE_DATE          VARCHAR2(20),
   STATUS               NUMBER(8)
);

COMMENT ON TABLE JAT_PL_SALE_STATISTICS_GRADE IS
'畅销款销售品质统计';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.ORG_ID IS
'组织';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.STYLE_MIDDLE_CLASS_ID IS
'款式中类';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.STYLE_ORNA_CLASS_ID IS
'款式小类';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.STYLE_ID IS
'款式';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.COLOR_GRADE_ID IS
'色级';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.CLEAN_ID IS
'净度';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.SIZE_ID IS
'尺寸';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.STOCK_NUM IS
'库存数量';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.ONWAY_NUM IS
'在途数量';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.YEAR_NUM IS
'上年销售';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.HALF_YEAR_NUM IS
'半年销售';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.HALF_YEAR_AVG_NUM IS
'半年平均销售';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.MONTH_NUM IS
'上月销售';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.CREATE_ID IS
'创建人';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.CREATE_DATE IS
'创建时间';

COMMENT ON COLUMN JAT_PL_SALE_STATISTICS_GRADE.STATUS IS
'状态';

/*==============================================================*/
/* Index: JAT_PL_SALE_STATISTICS_IDX1                           */
/*==============================================================*/
CREATE INDEX JAT_PL_SALE_STATISTICS_IDX1 ON JAT_PL_SALE_STATISTICS_GRADE (
   ORG_ID ASC,
   ITEM_CLASS_ID ASC,
   ORNA_CLASS_ID ASC,
   ANALYSIS_ID ASC,
   STYLE_ITEM_CLASS_ID ASC
);

/*==============================================================*/
/* Index: JAT_PL_SALE_STATISTICS_IDX2                           */
/*==============================================================*/
CREATE INDEX JAT_PL_SALE_STATISTICS_IDX2 ON JAT_PL_SALE_STATISTICS_GRADE (
   ORG_ID ASC,
   STYLE_ID ASC
);

/*==============================================================*/
/* Index: JAT_PL_SALE_STATISTICS_IDX3                           */
/*==============================================================*/
CREATE INDEX JAT_PL_SALE_STATISTICS_IDX3 ON JAT_PL_SALE_STATISTICS_GRADE (
   ORG_ID ASC,
   STYLE_ID ASC,
   COLOR_GRADE_ID ASC,
   CLEAN_ID ASC
);

/*==============================================================*/
/* View: JAV_PL_SALE_STATISTICS                                 */
/*==============================================================*/
CREATE OR REPLACE VIEW JAV_PL_SALE_STATISTICS AS
SELECT T.ORG_ID,
       T.ITEM_CLASS_ID,
       T.ORNA_CLASS_ID,
       T.ANALYSIS_ID,
       T.STYLE_ITEM_CLASS_ID,
       T.STYLE_MIDDLE_CLASS_ID,
       T.STYLE_ORNA_CLASS_ID,
       T.STYLE_ID,
       SUM(T.STOCK_NUM) STOCK_NUM,
       SUM(T.ONWAY_NUM) ONWAY_NUM,
       SUM(T.YEAR_NUM) YEAR_NUM,
       SUM(T.HALF_YEAR_NUM) HALF_YEAR_NUM,
       SUM(T.HALF_YEAR_AVG_NUM) HALF_YEAR_AVG_NUM,
       SUM(T.MONTH_NUM) MONTH_NUM
  FROM JAT_PL_SALE_STATISTICS_GRADE T
 GROUP BY T.ORG_ID,
          T.ITEM_CLASS_ID,
          T.ORNA_CLASS_ID,
          T.ANALYSIS_ID,
          T.STYLE_ITEM_CLASS_ID,
          T.STYLE_MIDDLE_CLASS_ID,
          T.STYLE_ORNA_CLASS_ID,
          T.STYLE_ID
WITH READ ONLY;

 COMMENT ON TABLE JAV_PL_SALE_STATISTICS IS
'畅销款销售统计';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.ORG_ID IS
'组织';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.ITEM_CLASS_ID IS
'大类';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.ORNA_CLASS_ID IS
'小类';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.ANALYSIS_ID IS
'分析范围';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.STYLE_ITEM_CLASS_ID IS
'款式大类';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.STYLE_MIDDLE_CLASS_ID IS
'款式中类';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.STYLE_ORNA_CLASS_ID IS
'款式小类';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.STYLE_ID IS
'款式';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.STOCK_NUM IS
'库存数量';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.ONWAY_NUM IS
'在途数量';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.YEAR_NUM IS
'上年销售';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.HALF_YEAR_NUM IS
'半年销售';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.HALF_YEAR_AVG_NUM IS
'半年平均销售';

COMMENT ON COLUMN JAV_PL_SALE_STATISTICS.MONTH_NUM IS
'上月销售';

ALTER TABLE JAT_PL_DISPLAY_ORGTYPE_LINE
   ADD CONSTRAINT FK_JAT_PL_DISPLAY_ORGTYPE_LINE FOREIGN KEY (HEADID)
      REFERENCES JAT_PL_DISPLAY_ORGTYPE_HEAD (HEADID);

ALTER TABLE JAT_PL_DISPLAY_STANDAND
   ADD CONSTRAINT FK_JAT_PL_DISPLAY_STANDAND FOREIGN KEY (HEADID)
      REFERENCES JAT_PL_DISPLAY_ORGTYPE_HEAD (HEADID);

ALTER TABLE JAT_PL_GATHER_ORDER_LINE
   ADD CONSTRAINT FK_JAT_PL_GATHER_ORDER_LINE FOREIGN KEY (HEADID)
      REFERENCES JAT_PL_GATHER_SET_HEAD (HEADID);

ALTER TABLE JAT_PL_GATHER_ORDER_LINE
   ADD CONSTRAINT FK_JAT_PL_GATHER_ORDER_LINE2 FOREIGN KEY (SET_LINE_ID)
      REFERENCES JAT_PL_GATHER_SET_LINE (LINEID);

ALTER TABLE JAT_PL_GATHER_SET_GRADE
   ADD CONSTRAINT FK_JAT_PL_GATHER_SET_GRADE FOREIGN KEY (LINEID)
      REFERENCES JAT_PL_GATHER_SET_LINE (LINEID);

ALTER TABLE JAT_PL_GATHER_SET_LINE
   ADD CONSTRAINT FK_JAT_PL_GATHER_SET_LINE FOREIGN KEY (HEADID)
      REFERENCES JAT_PL_GATHER_SET_HEAD (HEADID);

ALTER TABLE JAT_PL_GATHER_SET_SIZE
   ADD CONSTRAINT FK_JAT_PL_GATHER_SET_SIZE FOREIGN KEY (LINEID)
      REFERENCES JAT_PL_GATHER_SET_LINE (LINEID);

ALTER TABLE JAT_PL_GATHER_SET_STYLE
   ADD CONSTRAINT FK_JAT_PL_GATHER_SET_SALE FOREIGN KEY (LINEID)
      REFERENCES JAT_PL_GATHER_SET_LINE (LINEID);
