CREATE TABLE video_shop_customer(
    id number(10),
    name varchar2(10),
    tel_1 varchar2(20),
    tel_2 varchar2(20),
    address varchar2(20),
    email varchar2(20),
    CONSTRAINT video_shop_customer_id_pk primary key(id)
);
CREATE SEQUENCE video_shop_customer_id_seq;

ALTER TABLE video_shop_customer ADD CONSTRAINT vs_customer_tel_1_unique UNIQUE(tel_1);