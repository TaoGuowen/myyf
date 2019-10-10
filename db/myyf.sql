/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/7/12 18:43:20                           */
/*==============================================================*/


DROP TABLE IF EXISTS PT_DICT;

DROP TABLE IF EXISTS USER_ACCOUNT;

DROP TABLE IF EXISTS USER_CARD;

DROP TABLE IF EXISTS USER_MEMBER;

DROP TABLE IF EXISTS PRODUCT_CARD;

DROP TABLE IF EXISTS PRODUCT_CATEGORY;

DROP TABLE IF EXISTS PRODUCT_CHECK_ITEM;

DROP TABLE IF EXISTS PRODUCT_ORDER;

DROP TABLE IF EXISTS PRODUCT_PROPERTY;

DROP TABLE IF EXISTS PRODUCT_SIZE_CONFIG;

DROP TABLE IF EXISTS PRODUCT_SKU;

DROP TABLE IF EXISTS PRODUCT_SPU;

DROP TABLE IF EXISTS PT_KNOWLEDGE;

DROP TABLE IF EXISTS PAYMENT_TYPE;

DROP TABLE IF EXISTS PAYMENT_PAY;

DROP TABLE IF EXISTS PAYMENT_PTRADE;

DROP TABLE IF EXISTS PAYMENT_RTRADE;

/*==============================================================*/
/* Table: PT_DICT                                               */
/*==============================================================*/
CREATE TABLE PT_DICT
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   BUSI_TYPE            VARCHAR(64) NOT NULL COMMENT '类型',
   CODE_VALUE           VARCHAR(8) NOT NULL COMMENT '码值',
   CODE_NAME            VARCHAR(64) NOT NULL COMMENT '码值名称',
   SORT_NO              VARCHAR(64) NOT NULL COMMENT '排序号',
   REMARK               VARCHAR(512) COMMENT '备注',
   PRIMARY KEY (ID)
);

ALTER TABLE PT_DICT COMMENT '字典';

/*==============================================================*/
/* Table: USER_ACCOUNT                                          */
/*==============================================================*/
CREATE TABLE USER_ACCOUNT
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   USER_NAME            VARCHAR(64) NOT NULL COMMENT '用户名',
   PASSWORD             VARCHAR(128) NOT NULL COMMENT '密码',
   OPEND_ID             VARCHAR(64) NOT NULL COMMENT 'OPENID',
   STATUS_CODE          VARCHAR(8) NOT NULL COMMENT '状态',
   PRIMARY KEY (ID)
);

ALTER TABLE USER_ACCOUNT COMMENT '账户';

/*==============================================================*/
/* Table: USER_CARD                                             */
/*==============================================================*/
CREATE TABLE USER_CARD
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   USER_ID              VARCHAR(32) NOT NULL COMMENT '用户ID',
   ORDER_NO             VARCHAR(32) NOT NULL COMMENT '订单ID',
   CARD_TYPE            VARCHAR(8) NOT NULL COMMENT '卡类型',
   CARD_NAME            VARCHAR(64) NOT NULL COMMENT '卡名称',
   TOTAL_TIMES          SMALLINT NOT NULL COMMENT '总次数',
   REMAIND_TIMES        SMALLINT NOT NULL COMMENT '剩余次数',
   END_DAY              DATE NOT NULL COMMENT '截至日期',
   BUY_DAY              DATE NOT NULL COMMENT '购买日期',
   BUY_PRICE            NUMERIC(16,6) NOT NULL COMMENT '购买价格',
   STATUS_CODE          VARCHAR(8) NOT NULL COMMENT '是否有效',
   PRIMARY KEY (ID)
);

ALTER TABLE USER_CARD COMMENT '用户卡信息';

/*==============================================================*/
/* Table: USER_MEMBER                                           */
/*==============================================================*/
CREATE TABLE USER_MEMBER
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   USER_ID              VARCHAR(32) NOT NULL COMMENT '用户ID',
   NAME                 VARCHAR(64) NOT NULL COMMENT '姓名',
   SEX                  CHAR(1) NOT NULL COMMENT '性别',
   BIRTH_DAY            DATE NOT NULL COMMENT '出生年月',
   WEIGHT               SMALLINT NOT NULL COMMENT '体重',
   HEIGHT               SMALLINT NOT NULL COMMENT '身高',
   RELATION             VARCHAR(8) NOT NULL COMMENT '关系',
   MOBILE               INT(11) NOT NULL COMMENT '手机',
   PRIMARY KEY (ID)
);

ALTER TABLE USER_MEMBER COMMENT '家庭成员';


/*==============================================================*/
/* Table: PRODUCT_CARD                                          */
/*==============================================================*/
CREATE TABLE PRODUCT_CARD
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   TYPE_CODE            VARCHAR(8) NOT NULL COMMENT '卡类型 参见码表（01:次卡；02：月卡；03：活动卡）',
   NAME                 VARCHAR(64) NOT NULL COMMENT '名称',
   TIMES                SMALLINT NOT NULL COMMENT '次卡使用次数
            月卡使用次数为-1
            ',
   END_DATE             DATE NOT NULL COMMENT '次卡 截至日期
            月卡截至日期： 1979/1/1',
   TTL                  SMALLINT NOT NULL COMMENT '次卡有效期 为 -1
            月卡有效期 为真实有效期如30天',
   PRICE                NUMERIC(16,6) NOT NULL COMMENT '价格',
   OPRICE               NUMERIC(16,6) NOT NULL COMMENT '原始价格',
   `LIMIT`              SMALLINT NOT NULL COMMENT '最大购买次数 默认：-1（无限量）',
   AMOUNT               SMALLINT NOT NULL COMMENT '库存 默认：-1（无限量）',
   SORT_NO              SMALLINT NOT NULL COMMENT '排序号',
   MEMO                 VARCHAR(512) COMMENT '描述',
   PRIMARY KEY (ID)
);

ALTER TABLE PRODUCT_CARD COMMENT '卡';

/*==============================================================*/
/* Table: PRODUCT_CATEGORY                                      */
/*==============================================================*/
CREATE TABLE PRODUCT_CATEGORY
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   CODE                 VARCHAR(8) COMMENT '代码',
   NAME                 VARCHAR(64) COMMENT '名称',
   PID                  VARCHAR(32) COMMENT '父级ID',
   SORT_NO              INT COMMENT '排序号',
   PRIMARY KEY (ID)
);

ALTER TABLE PRODUCT_CATEGORY COMMENT '本表记录产品分类信息
  如：
    一级：女装/内衣
        ';

/*==============================================================*/
/* Table: PRODUCT_CHECK_ITEM                                    */
/*==============================================================*/
CREATE TABLE PRODUCT_CHECK_ITEM
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   CODE                 VARCHAR(8) COMMENT '码值',
   NAME                 VARCHAR(64) COMMENT '名称',
   COVER_CODES          VARCHAR(512) COMMENT '遮挡编码',
   SORT_NO              INT COMMENT '排序号',
   PRIMARY KEY (ID)
);

ALTER TABLE PRODUCT_CHECK_ITEM COMMENT '检测项目';

/*==============================================================*/
/* Table: PRODUCT_ORDER                                         */
/*==============================================================*/
CREATE TABLE PRODUCT_ORDER
(
   NO                   VARCHAR(32) NOT NULL COMMENT '编号',
   USER_ID              VARCHAR(32) NOT NULL COMMENT '用户ID',
   SPU_ID               VARCHAR(32) NOT NULL COMMENT '产品ID',
   SKU_ID               VARCHAR(32) COMMENT '商品ID',
   SPU_NAME             VARCHAR(64) NOT NULL COMMENT '产品名称',
   SKU_NAME             VARCHAR(64) COMMENT '商品名称',
   TYPE_CODE            VARCHAR(8) COMMENT '订单类型： 01：产品类；02：卡片类
            注：因卡片类订单在处理逻辑上比产品类订单多1个环节：支付成功后需要生成用户卡片信息，故此处加以区分',
   CODE                 VARCHAR(32) COMMENT '码 用于生成二维码或条码等，',
   MONEY                NUMERIC(16,6) COMMENT '金额',
   PAY_TYPE             VARCHAR(8) COMMENT '支付类型： 01：卡支付，02：直接支付',
   CARD_INFO_ID         VARCHAR(32) COMMENT '卡片ID',
   NAME                 VARCHAR(64) NOT NULL COMMENT '联系人',
   MOBILE               INT(11) NOT NULL COMMENT '联系方式',
   CREATE_TIME          DATE NOT NULL COMMENT '创建日期',
   STATUS_CODE          VARCHAR(8) NOT NULL COMMENT '状态 01:待支付
            02:已支付
            06:已退款
            09:已删除',
   TRADE_ID             VARCHAR(32) COMMENT '支付流水ID 交易流水完成后 回填',
   PAY_METHOD           VARCHAR(8) COMMENT '支付方式 交易流水完成后 回填
            支付方式，参见T_PAYMENT_METHOD',
   PRIMARY KEY (NO)
);

ALTER TABLE PRODUCT_ORDER COMMENT '订单';

/*==============================================================*/
/* Table: PRODUCT_PROPERTY                                      */
/*==============================================================*/
CREATE TABLE PRODUCT_PROPERTY
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   CODE                 VARCHAR(8) COMMENT '码值',
   NAME                 VARCHAR(64) COMMENT '名称',
   DATA_TYPE            VARCHAR(8) COMMENT '属性类型',
   SORT_NO              INT COMMENT '排序号'
);

ALTER TABLE PRODUCT_PROPERTY COMMENT '产品属性
本表记录产品属性：
1、color、颜色、varchar(16)
2、si';

/*==============================================================*/
/* Table: PRODUCT_SIZE_CONFIG                                   */
/*==============================================================*/
CREATE TABLE PRODUCT_SIZE_CONFIG
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   CODE                 VARCHAR(8) COMMENT '码值',
   NAME                 VARCHAR(32) COMMENT '名称',
   LOW                  NUMERIC(16,6) COMMENT '身高下限',
   UP                   NUMERIC(16,6) COMMENT '身高上限',
   SORT_NO              INT COMMENT '排序号',
   PRIMARY KEY (ID)
);

ALTER TABLE PRODUCT_SIZE_CONFIG COMMENT '身高尺码配置表';

/*==============================================================*/
/* Table: PRODUCT_SKU                                           */
/*==============================================================*/
CREATE TABLE PRODUCT_SKU
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   SPU_ID               VARCHAR(32) COMMENT '产品ID',
   NAME                 VARCHAR(64) NOT NULL COMMENT '名称',
   SPECS                VARCHAR(1024) COMMENT '规格 该字段用于记录该商品的所有属性名+属性值，如“颜色：red，尺码：xxl”，等，
            重要字段或共有字段单独成为字段（如尺码） 用于快速解析',
   CHECK_CODE           VARCHAR(8) NOT NULL COMMENT '检查项目',
   COLOR_CODE           VARCHAR(8) COMMENT '颜色',
   SIZE_CODE            VARCHAR(8) NOT NULL COMMENT '尺码',
   PRICE                NUMERIC(16,6) COMMENT '价格',
   OPRICE               NUMERIC(16,6) COMMENT '原始价',
   AMOUNT               BIGINT NOT NULL COMMENT '库存',
   SORT_NO              INT NOT NULL COMMENT '排序号',
   MEMO                 VARCHAR(512) COMMENT '描述',
   PRIMARY KEY (ID)
);

ALTER TABLE PRODUCT_SKU COMMENT '商品';

/*==============================================================*/
/* Table: PRODUCT_SPU                                           */
/*==============================================================*/
CREATE TABLE PRODUCT_SPU
(
   ID                   VARCHAR(32) NOT NULL COMMENT '主键',
   CATEGORY_ID          VARCHAR(32) COMMENT '产品分类ID',
   NAME                 VARCHAR(64) NOT NULL COMMENT '产品名称',
   SPECS                VARCHAR(1024) COMMENT '产品规格 该字段用于记录该商品的所有属性名 如“颜色，尺码，等，
            注：不记录属性值，属性值 商品表记录
            主要用于录入商品时 快速定位属性值
            ',
   SORT_NO              SMALLINT NOT NULL COMMENT '排序号',
   MEMO                 VARCHAR(512) COMMENT '描述',
   PRIMARY KEY (ID)
);

ALTER TABLE PRODUCT_SPU COMMENT '产品 本表记录产品信息：如防护服1';


/*==============================================================*/
/* Table: PT_KNOWLEDGE                                          */
/*==============================================================*/
CREATE TABLE PT_KNOWLEDGE
(
   ID                   VARCHAR(32) NOT NULL COMMENT 'ID',
   TYPE_CODE            VARCHAR(8) NOT NULL COMMENT '类型',
   TITLE                VARCHAR(64) NOT NULL COMMENT '标题',
   CONTENT              VARCHAR(1024) NOT NULL COMMENT '内容',
   EXPERT               VARCHAR(512) NOT NULL COMMENT '专家',
   STATUS_CODE          VARCHAR(8) NOT NULL COMMENT '状态',
   PRIMARY KEY (ID)
);

ALTER TABLE PT_KNOWLEDGE COMMENT '知识';

/*==============================================================*/
/* Table: PAYMENT_TYPE                                          */
/*==============================================================*/
CREATE TABLE PAYMENT_TYPE
(
   ID                   VARCHAR(32) NOT NULL,
   CODE                 VARCHAR(8),
   NAME                 VARCHAR(64),
   STYLE_CLASS          CHAR(10),
   PAY_URI              VARCHAR(32),
   SORT_NO              INT
);

ALTER TABLE PAYMENT_TYPE COMMENT '支付方式';

/*==============================================================*/
/* Table: PAYMENT_PAY                                           */
/*==============================================================*/
CREATE TABLE PAYMENT_PAY
(
   ID                   NATIONAL VARCHAR(32) NOT NULL COMMENT '主键',
   NO                   NATIONAL VARCHAR(32) NOT NULL COMMENT '支付号',
   PTRADE_ID            NATIONAL VARCHAR(32) NOT NULL COMMENT '支付交易id',
   PAY_METHOD           VARCHAR(8) COMMENT '支付方式（01：微信， 02:支付宝，...）',
   REQ_TIME             DATETIME NOT NULL COMMENT '支付请求时间',
   RES_TIME             DATETIME COMMENT '支付响应时间',
   RES_MSG              NATIONAL VARCHAR(2000) COMMENT '支付响应信息',
   RESNO                NATIONAL VARCHAR(64) COMMENT '第三方交易号，部分第三方退款时需要',
   STATUS_CODE          VARCHAR(8) NOT NULL COMMENT '状态：01：init,  02:成功, 09:失败,',
   PRIMARY KEY (ID),
   KEY NO (NO)
);

ALTER TABLE PAYMENT_PAY COMMENT '支付';

#DROP INDEX T_PAYMENT_PAY_REQTIME ON PAYMENT_PAY;
/*==============================================================*/
/* Index: T_PAYMENT_PAY_REQTIME                                 */
/*==============================================================*/
CREATE INDEX T_PAYMENT_PAY_REQTIME ON PAYMENT_PAY
(
   REQ_TIME
);

/*==============================================================*/
/* Table: PAYMENT_PTRADE                                        */
/*==============================================================*/
CREATE TABLE PAYMENT_PTRADE
(
   ID                   NATIONAL VARCHAR(32) NOT NULL COMMENT '主键',
   TITLE                NATIONAL VARCHAR(500) NOT NULL COMMENT '标题',
   CASH                 DECIMAL(7,2) NOT NULL COMMENT '金额',
   CJTIME               DATETIME NOT NULL COMMENT '交易创建时间',
   STATUS_CODE          VARCHAR(8) NOT NULL COMMENT '状态：01：init,  02:成功, 09:失败,',
   PAYID                NATIONAL VARCHAR(32) COMMENT '支付成功记录id',
   PRIMARY KEY (ID)
);

ALTER TABLE PAYMENT_PTRADE COMMENT '支付流水';

#DROP INDEX T_PAYMENT_PTRADE_CJTIME ON PAYMENT_PTRADE;
/*==============================================================*/
/* Index: T_PAYMENT_PTRADE_CJTIME                               */
/*==============================================================*/
CREATE INDEX T_PAYMENT_PTRADE_CJTIME ON PAYMENT_PTRADE
(
   CJTIME
);

/*==============================================================*/
/* Table: PAYMENT_RTRADE                                        */
/*==============================================================*/
CREATE TABLE PAYMENT_RTRADE
(
   ID                   NATIONAL VARCHAR(32) NOT NULL COMMENT '主键',
   PTRADEID             NATIONAL VARCHAR(32) NOT NULL COMMENT '支付交易id',
   CASH                 DECIMAL(5,1) NOT NULL COMMENT '退款金额',
   CJTIME               DATETIME NOT NULL COMMENT '退款交易创建时间',
   REQTIME              DATETIME NOT NULL COMMENT '支付请求时间',
   RESTIME              DATETIME COMMENT '支付响应时间',
   RESMSG               NATIONAL VARCHAR(2000) COMMENT '支付响应信息',
   STATUS_CODE          VARCHAR(8) NOT NULL COMMENT '状态：01：init,  02:成功, 09:失败,',
   PRIMARY KEY (ID)
);

ALTER TABLE PAYMENT_RTRADE COMMENT '退款流水';

#DROP INDEX T_PAYMENT_RTRADE_CJTIME ON PAYMENT_RTRADE;
/*==============================================================*/
/* Index: T_PAYMENT_RTRADE_CJTIME                               */
/*==============================================================*/
CREATE INDEX T_PAYMENT_RTRADE_CJTIME ON PAYMENT_RTRADE
(
   CJTIME
);


/*ALTER TABLE USER_CARD ADD CONSTRAINT FK_ORDER_ID FOREIGN KEY (ORDER_NO)
      REFERENCES PRODUCT_ORDER (NO) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE USER_CARD ADD CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID)
      REFERENCES USER_ACCOUNT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE USER_MEMBER ADD CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID)
      REFERENCES USER_ACCOUNT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRODUCT_ORDER ADD CONSTRAINT FK_SKU_ID FOREIGN KEY (SKU_ID)
      REFERENCES PRODUCT_SKU (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRODUCT_ORDER ADD CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID)
      REFERENCES USER_ACCOUNT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRODUCT_SKU ADD CONSTRAINT FK_SPU_ID FOREIGN KEY (SPU_ID)
      REFERENCES PRODUCT_SPU (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PRODUCT_SPU ADD CONSTRAINT FK_CATEGROY_ID FOREIGN KEY (CATEGORY_ID)
      REFERENCES PRODUCT_CATEGORY (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PAYMENT_PAY ADD CONSTRAINT FK_PTRADE_ID FOREIGN KEY (PTRADE_ID)
      REFERENCES PAYMENT_PTRADE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE PAYMENT_RTRADE ADD CONSTRAINT FK_PTRADE_ID FOREIGN KEY (PTRADEID)
      REFERENCES PAYMENT_PTRADE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;*/
