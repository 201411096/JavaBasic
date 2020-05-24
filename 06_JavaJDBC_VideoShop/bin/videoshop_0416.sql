--sequence 확인법
select * from user_sequences;
/***** CREATE TABLE VIDEO_SHOP_CUSTOMER START *****/
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
/***** CREATE TABLE VIDEO_SHOP_CUSTOMER END *****/
ALTER TABLE video_shop_customer ADD CONSTRAINT vs_customer_tel_1_unique UNIQUE(tel_1);


/***** CREATE TABLE VIDEO_SHOP_VIDEO START *****/
CREATE TABLE video_shop_video(
    vid number(10),
    vgenre varchar2(20),
    vname varchar2(20) not null,
    vdirector varchar2(20),
    vactor varchar2(20),
    vdetail varchar2(1024),
    CONSTRAINT video_shop_video_vid_pk primary key(vid)
);
/***** CREATE TABLE VIDEO_SHOP_VIDEO END *****/
SELECT * FROM VIDEO_SHOP_VIDEO WHERE VNAME LIKE '%용용%';
/***** CREATE TABLE VIDEO_SHOP_MANAGEMENT START *****/
CREATE TABLE video_shop_video_management(
    vmid number(10),
    vid number(10),
    CONSTRAINT video_shop_video_m_vmid primary key(vmid),
    CONSTRAINT video_shop_video_m_vid foreign key(vid) references video_shop_video(vid)
);
CREATE SEQUENCE video_shop_video_vid_seq start with 0 minvalue 0;
CREATE SEQUENCE video_shop_vm_vmid_seq start with 0 minvalue 0;
/***** CREATE TABLE VIDEO_SHOP_MANAGEMENT END *****/
/***** CREATE TABLE VIDEO_SHOP_RENTAL START *****/
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
CREATE SEQUENCE video_shop_vr_vrid_seq;
/***** CREATE TABLE VIDEO_SHOP_RENTAL END *****/
SELECT name FROM video_shop_customer WHERE tel_1=?;

--대여전에 확인하는문장
SELECT * FROM video_shop_rental WHERE VMID=? AND returndate IS NULL;
--대여전에 확인해줘야함
SELECT * FROM video_shop_rental WHERE VMID=47 AND returndate IS NULL;
INSERT INTO video_shop_rental(VRID, RENTALDATE, VMID, ID) VALUES(video_shop_vr_vrid_seq.NEXTVAL, SYSDATE, 3, 2);
INSERT INTO video_shop_rental(VRID, RENTALDATE, VMID, ID) VALUES(video_shop_vr_vrid_seq.NEXTVAL, SYSDATE, ?, (SELECT ID FROM video_shop_customer WHERE tel_1=?) );
SELECT ID FROM video_shop_customer WHERE name=?;

UPDATE video_shop_rental SET returndate=SYSDATE WHERE VMID=? AND returndate IS NULL;
select to_char(rentaldate, 'yyyy/mm/dd hh24:mi:ss') from video_shop_rental;

select * from video_shop_rental; --비디오번호 제목 고객명 전화번호 반납예정일(대여날짜에 +3) 반납여부

SELECT R.VMID, V.VNAME, VC.NAME, vc.tel_1, r.rentaldate+3, '미납'
FROM VIDEO_SHOP_RENTAL R
INNER JOIN VIDEO_SHOP_VIDEO_MANAGEMENT VM
ON R.vmid=vm.vmid
INNER JOIN VIDEO_SHOP_VIDEO V
ON VM.VID = V.vid
INNER JOIN video_shop_customer VC
ON VC.ID = R.ID
WHERE r.returndate is null;