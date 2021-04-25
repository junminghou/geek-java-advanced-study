# 第6周作业


#### Week06 作业题目：
>6.（必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

```sql
CREATE TABLE order_sale
(
    id                  BIGINT AUTO_INCREMENT
        COMMENT '主键ID'
        PRIMARY KEY,
    order_no            VARCHAR(30) NULL
        COMMENT '订单号',
    status              TINYINT NULL
        COMMENT '订单状态,1-待审核,2-待确认，3-已拒绝，4-完成，5-已取消，6-配送中，8-待支付，9-待配送，10-配送完成',
    user_id             BIGINT NULL
        COMMENT '下单用户ID',
    pay_price           DECIMAL(16, 2) NULL
        COMMENT '实际支付价格',
    total_discount_rate DECIMAL(16, 2) NULL
        COMMENT '订单总折扣率',
    express_fee         DECIMAL(16, 2) NULL
        COMMENT '快递费用，单位分',
    real_create_user_id BIGINT NULL
        COMMENT '实际下单用户ID',
    finish_time         DATETIME NULL
        COMMENT '订单完成时间',
    delete_status       TINYINT   DEFAULT '2' NULL
        COMMENT '删除状态，1-已删除，2-正常',
    gmt_create          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    gmt_modify          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP  
)
  COMMENT '订单表'
  ENGINE = InnoDB;

CREATE TABLE order_sale_item
(
  id                         BIGINT AUTO_INCREMENT
  COMMENT '主键ID'
    PRIMARY KEY,
  order_id                   BIGINT                              NULL
  COMMENT '订单ID',
  purchase_company_id        BIGINT                              NULL
  COMMENT '采购公司ID',
  sale_company_id            BIGINT                              NULL
  COMMENT '销售公司ID',
  item_code                  CHAR(64)                            NULL
  COMMENT '商品编码',
  item_id                    BIGINT                              NULL
  COMMENT '商品ID',
  item_name                  VARCHAR(255)                        NULL
  COMMENT '商品名称',
  sku_id                     BIGINT                              NULL
  COMMENT 'SKU ID',
  sku_name                   VARCHAR(255)                        NULL
  COMMENT 'SKU名称',
  image_url                  VARCHAR(200)                        NULL
  COMMENT '商品主图片',
  item_num                   INT(10)                             NULL
  COMMENT '商品数量',
  item_market_price          DECIMAL(16, 2)                      NULL
  COMMENT '商品市场单价，单位分',
  item_price                 DECIMAL(16, 2)                      NULL
  COMMENT '商品实际售卖单价，单位分',
  item_real_price            DECIMAL(16, 2)                      NULL
  COMMENT '商品实付价格，单位分',
  gmt_create                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
  gmt_modify                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
)
  COMMENT '订单商品明细表'
  ENGINE = InnoDB;

CREATE TABLE item
(
  id                  BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  company_id          BIGINT UNSIGNED                     NOT NULL,
  code                CHAR(64)                            NULL,
  name                CHAR(150)                           NULL,
  category            TINYINT                             NULL
  image_url           VARCHAR(2048)                       NULL,
  gmt_create          DATETIME DEFAULT CURRENT_TIMESTAMP  NULL,
  gmt_modify          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
)
  COMMENT '商品表'
  ENGINE = InnoDB;

CREATE TABLE item_sku
(
  id                     BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  code                   CHAR(64)                            NULL
  COMMENT '规格商品编码',
  company_id             BIGINT UNSIGNED                     NOT NULL,
  item_id                BIGINT UNSIGNED                     NOT NULL,
  show_price             DECIMAL                             NOT NULL,
  real_price             DECIMAL                             NOT NULL,
  name                   VARCHAR(64)                         NOT NULL,
  stock                  INT                                 NOT NULL,
  image_url              VARCHAR(200)                        NULL,
  gmt_create             DATETIME DEFAULT CURRENT_TIMESTAMP  NULL,
  gmt_modify             TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  description            VARCHAR(1000)                       NULL
  COMMENT '商品介绍',
  delete_status          TINYINT DEFAULT '1'                 NOT NULL
  COMMENT '删除状态，1-正常，2-已删除'
)
  ENGINE = InnoDB;

CREATE TABLE item_sku_stock
(
  id                  BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  company_id          BIGINT UNSIGNED                     NOT NULL
  COMMENT '所属公司id',
  sku_id              BIGINT UNSIGNED                     NOT NULL
  COMMENT '关联的sku_id',
  sku_code            CHAR(64)                            NOT NULL
  COMMENT '规格商品编码',
  item_name           CHAR(150)                           NULL
  COMMENT '商品名',
  sku_name            VARCHAR(64)                         NULL
  COMMENT '规格名',
  warehouse_code      VARCHAR(128)                        NOT NULL
  COMMENT '库房的编码',
  warehouse_desc      VARCHAR(150)                        NULL
  COMMENT '仓库说明',
  sku_allocation      VARCHAR(50)                         NOT NULL
  COMMENT '库位',
  stock               BIGINT                              NOT NULL
  COMMENT '库存',
  gmt_create          DATETIME DEFAULT CURRENT_TIMESTAMP  NULL,
  gmt_modify          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  status              TINYINT DEFAULT '1'                 NULL
  COMMENT '状态，1-正常，2-已删除'
)
  ENGINE = InnoDB;

CREATE TABLE user
(
  id             BIGINT                              NOT NULL
  COMMENT '用户ID'
    PRIMARY KEY,
  user_name      VARCHAR(50)                         NULL
  COMMENT '用户账号',
  nick_name      VARCHAR(50)                         NULL
  COMMENT '昵称',
  mobile         VARCHAR(50)                         NULL
  COMMENT '手机号',
  user_type      INT                                 NULL
  gmt_create     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  gmt_modify     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
)
  COMMENT '用户表'
  ENGINE = InnoDB;
```

