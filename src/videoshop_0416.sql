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



CREATE TABLE video_shop_video(
    vid number(10),
    vgenre varchar2(20),
    vname varchar2(20) not null,
    vdirector varchar2(20),
    vactor varchar2(20),
    vdetail varchar2(1024),
    CONSTRAINT video_shop_video_vid_pk primary key(vid)
);
SELECT * FROM VIDEO_SHOP_VIDEO WHERE VNAME LIKE '%용용%';

CREATE TABLE video_shop_video_management(
    vmid number(10),
    vid number(10),
    CONSTRAINT video_shop_video_m_vmid primary key(vmid),
    CONSTRAINT video_shop_video_m_vid foreign key(vid) references video_shop_video(vid)
);
CREATE SEQUENCE video_shop_video_vid_seq start with 0 minvalue 0;
CREATE SEQUENCE video_shop_vm_vmid_seq start with 0 minvalue 0;


CREATE TABLE video_shop_rental(
    vrid number(10),
    rentaldate date,
    returndate date,
    vmid number(10),
    id number(10), -- customer 이름
    CONSTRAINT video_shop_rental_pk PRIMARY KEY(vrid),
    CONSTRAINT video_shop_rental_fk_vmid FOREIGN KEY(vmid) REFERENCES video_shop_video_management(vmid),
    CONSTRAINT video_shop_rental_fk_id FOREIGN KEY(id) REFERENCES video_shop_customer(id)
    
);
/*
drop table video_shop_video;
drop table video_shop_video_management;
*/
