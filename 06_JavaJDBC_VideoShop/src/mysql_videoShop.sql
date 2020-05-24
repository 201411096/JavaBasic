create database videoshop default character set UTF8;
use videoshop;
select * from user_sequences;
/***** CREATE TABLE VIDEO_SHOP_CUSTOMER START *****/
CREATE TABLE video_shop_customer(
    id int(10) AUTO_INCREMENT,
    name varchar(10),
    tel_1 varchar(20),
    tel_2 varchar(20),
    address varchar(20),
    email varchar(20),
    CONSTRAINT video_shop_customer_id_pk primary key(id)
);
select * from video_shop_customer;

drop table video_shop_customer;
drop table video_shop_video;
drop table video_shop_video_management;
drop table video_shop_rental;
/***** CREATE TABLE VIDEO_SHOP_CUSTOMER END *****/
ALTER TABLE video_shop_customer ADD CONSTRAINT vs_customer_tel_1_unique UNIQUE(tel_1);
select * from video_shop_customer;

/***** CREATE TABLE VIDEO_SHOP_VIDEO START *****/
CREATE TABLE video_shop_video(
    vid int(10)  AUTO_INCREMENT,
    vgenre varchar(20),
    vname varchar(20) not null,
    vdirector varchar(20),
    vactor varchar(20),
    vdetail varchar(1024),
    CONSTRAINT video_shop_video_vid_pk primary key(vid)
);
/***** CREATE TABLE VIDEO_SHOP_VIDEO END *****/
SELECT * FROM VIDEO_SHOP_VIDEO WHERE VNAME LIKE '%���%';
/***** CREATE TABLE VIDEO_SHOP_MANAGEMENT START *****/
CREATE TABLE video_shop_video_management(
    vmid int(10)  AUTO_INCREMENT,
    vid int(10),
    CONSTRAINT video_shop_video_m_vmid primary key(vmid),
    CONSTRAINT video_shop_video_m_vid foreign key(vid) references video_shop_video(vid)
);

/***** CREATE TABLE VIDEO_SHOP_MANAGEMENT END *****/
/***** CREATE TABLE VIDEO_SHOP_RENTAL START *****/
CREATE TABLE video_shop_rental(
    vrid int(10)  AUTO_INCREMENT,
    rentaldate date,
    returndate date,
    vmid int(10),
    id int(10), -- customer �̸�
    CONSTRAINT video_shop_rental_pk PRIMARY KEY(vrid),
    CONSTRAINT video_shop_rental_fk_vmid FOREIGN KEY(vmid) REFERENCES video_shop_video_management(vmid),
    CONSTRAINT video_shop_rental_fk_id FOREIGN KEY(id) REFERENCES video_shop_customer(id)
    
);

/***** CREATE TABLE VIDEO_SHOP_RENTAL END *****/
SELECT name FROM video_shop_customer WHERE tel_1=?;


SELECT * FROM video_shop_rental WHERE VMID=? AND returndate IS NULL;

SELECT * FROM video_shop_rental WHERE VMID=47 AND returndate IS NULL;
INSERT INTO video_shop_rental(VRID, RENTALDATE, VMID, ID) VALUES(video_shop_vr_vrid_seq.NEXTVAL, SYSDATE, 3, 2);
INSERT INTO video_shop_rental(VRID, RENTALDATE, VMID, ID) VALUES(video_shop_vr_vrid_seq.NEXTVAL, SYSDATE, ?, (SELECT ID FROM video_shop_customer WHERE tel_1=?) );
SELECT ID FROM video_shop_customer WHERE name=?;

UPDATE video_shop_rental SET returndate=SYSDATE WHERE VMID=? AND returndate IS NULL;
select to_char(rentaldate, 'yyyy/mm/dd hh24:mi:ss') from video_shop_rental;



SELECT R.VMID, V.VNAME, VC.NAME, vc.tel_1, r.rentaldate+3, '�̳�'
FROM VIDEO_SHOP_RENTAL R
INNER JOIN VIDEO_SHOP_VIDEO_MANAGEMENT VM
ON R.vmid=vm.vmid
INNER JOIN VIDEO_SHOP_VIDEO V
ON VM.VID = V.vid
INNER JOIN video_shop_customer VC
ON VC.ID = R.ID
WHERE r.returndate is null;