/****************************** TABLE STRUCTURE ******************************/
/***** POSITION TABLE *****/
CREATE TABLE POS(
    POSID NUMBER(2),
    POSNAME VARCHAR2(20),
    CONSTRAINT POS_POSID_PK PRIMARY KEY(POSID)
);
/***** EMPLOYEE TABLE *****/
CREATE TABLE EMPLOYEE(
    EID VARCHAR2(20),
    EPASSWORD VARCHAR2(20),
    ENAME VARCHAR2(20),
    TEL VARCHAR2(20) UNIQUE,
    SAL  NUMBER(20),
    HIRE_DATE DATE,
    AGE NUMBER(3),
    POSID  NUMBER(2),
    CONSTRAINT EMPLOYEE_EID_PK PRIMARY KEY(EID),
    CONSTRAINT EMPLOYEE_POSID_FK FOREIGN KEY(POSID) REFERENCES POS(POSID)
);
/***** PRODUCTGROUP TABLE *****/
CREATE TABLE PRODUCTGROUP(
    PGROUPID NUMBER(10),
    PGROUPNAME VARCHAR(20) NOT NULL,
    CONSTRAINT productgroup_pk PRIMARY KEY(PGROUPID)
);
/***** PRODUCT TABLE *****/
CREATE TABLE PRODUCT(
    PID NUMBER(10),
    PGROUPID NUMBER(10) NOT NULL,
    PNAME VARCHAR(20) NOT NULL UNIQUE,
    PDETAIL VARCHAR(200),
    PPRICE NUMBER(10) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY(PID),
    CONSTRAINT product_fk_pgroupname FOREIGN KEY(PGROUPID) REFERENCES PRODUCTGROUP(PGROUPID)
);
CREATE SEQUENCE PRODUCT_PID_SEQ MAXVALUE 1000 CYCLE;
/***** PRODUCTMANAGEMENT TABLE *****/
CREATE TABLE PRODUCTMANAGEMENT(
    PMANAGEMENTID NUMBER(10),
    PID NUMBER(10),
    PDATE DATE,
    CONSTRAINT productmanagement_pk PRIMARY KEY(PMANAGEMENTID),
    CONSTRAINT productmanagement_fk_pid FOREIGN KEY(PID) REFERENCES PRODUCT(PID)
);
CREATE SEQUENCE PM_PMANAGEMENTID_SEQ MAXVALUE 1000000 CYCLE; --��ǰ������ ����� ������
//****************************** DROP TABLE ******************************/
DROP TABLE EMPLOYEE;
DROP TABLE POS;
DROP TABLE PRODUCTGROUP;
DROP TABLE PRODUCT;
DROP SEQUENCE PRODUCT_PID_SEQ;
DROP TABLE PRODUCTMANAGEMENT;
DROP SEQUENCE PM_PMANAGEMENTID_SEQ;
/****************************** TEST ******************************/
/***** EMPLOYEE TABLE TEST *****/

/* EMPLOYEE TABLE TEST CASE */
INSERT INTO EMPLOYEE(EID, EPASSWORD, ENAME, TEL, SAL, HIRE_DATE, AGE, POSID) VALUES(?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO EMPLOYEE(EID, EPASSWORD, ENAME, TEL, SAL, HIRE_DATE, AGE, POSID) VALUES('admin', 'admin', 'admin', '00-000-0000', '100000', '200415', 45, 0);
INSERT INTO EMPLOYEE(EID, EPASSWORD, ENAME, TEL, SAL, HIRE_DATE, AGE, POSID) VALUES('abc123', 'abc123', 'aname', '111-1111', '20000', '200416',  30,1);
INSERT INTO EMPLOYEE(EID, EPASSWORD, ENAME, TEL, SAL, HIRE_DATE, AGE, POSID) VALUES('def456', 'def456', 'dname', '222-2222', '10000', '200417', 22, 2);
INSERT INTO EMPLOYEE(EID, EPASSWORD, ENAME, TEL, SAL, HIRE_DATE, AGE, POSID) VALUES('aaa111', 'aaa111', 'aname', '333-3333', '10000', '200417', 20, 2);

SELECT * FROM EMPLOYEE WHERE eid = ?;
SELECT e.eid AS eid, e.epassword AS epassword, e.ename AS ename, e.tel AS tel, e.sal AS sal, e.hire_date AS hire_date, p.posname AS posname FROM EMPLOYEE e INNER JOIN POS p ON e.POSID=p.POSID WHERE e.eid='?';
SELECT * FROM EMPLOYEE WHERE eid = 'admin';
SELECT * FROM EMPLOYEE e INNER JOIN POS p ON e.POSID=p.POSID WHERE e.eid='admin';
SELECT e.eid AS eid, e.epassword AS epassword, e.ename AS ename, e.tel AS tel, e.sal AS sal, e.hire_date AS hire_date, p.posname AS posname FROM EMPLOYEE e INNER JOIN POS p ON e.POSID=p.POSID WHERE e.eid='admin';
/*SEARCH EMPLOYEE*/
SELECT E.ENAME AS ENAME, E.EID AS EID, E.TEL AS ETEL, P.POSNAME AS POSNAME, E.AGE AS EAGE, E.SAL AS SAL, TO_CHAR(E.HIRE_DATE, 'YYYY/MM/DD') AS HIRE_DATE FROM EMPLOYEE E INNER JOIN POS P ON E.POSID=P.POSID WHERE E.ENAME LIKE '%aname%';
SELECT E.ENAME AS ENAME, E.EID AS EID, E.TEL AS ETEL, P.POSNAME AS POSNAME, E.AGE AS EAGE, E.SAL AS SAL, TO_CHAR(E.HIRE_DATE, 'YYYY/MM/DD') AS HIRE_DATE FROM EMPLOYEE E INNER JOIN POS P ON E.POSID=P.POSID WHERE E.EID LIKE '%abc%';
/*UPDATE EMPLOYEE*/
UPDATE EMPLOYEE SET ENAME=?, POSID=?, TEL=?, HIRE_DATE =?, SAL=?, AGE=? WHERE EID=?;
UPDATE EMPLOYEE SET ENAME='aaaname', POSID=2, TEL='333-3333-3333', HIRE_DATE ='200418', SAL=5000, AGE=23 WHERE EID='aaa111';
/*DELETE EMPLOYEE*/
DELETE FROM EMPLOYEE WHERE eid=?;
DELETE FROM EMPLOYEE WHERE eid='bbb123456';

/***** POSITION TABLE TEST *****/
/* POS TABLE TEST CASE */
INSERT INTO POS(POSID, POSNAME) VALUES(?, ?);
INSERT INTO POS(POSID, POSNAME) VALUES(0, 'ADMIN');
INSERT INTO POS(POSID, POSNAME) VALUES(1, 'MANAGER');
INSERT INTO POS(POSID, POSNAME) VALUES(2, 'SALESMAN');

/***** PRODUCTGROUP TABLE TEST *****/
/* PRODUCTGROUP TABLE TEST CASE */
INSERT INTO productgroup(pgroupid, pgroupname)  VALUES(0, '���θ޴�');
INSERT INTO productgroup(pgroupid, pgroupname)  VALUES(1, '���̵�޴�');
INSERT INTO productgroup(pgroupid, pgroupname)  VALUES(2, '�����');
INSERT INTO productgroup(pgroupid, pgroupname)  VALUES(3, '��Ʈ�޴�');

/***** PRODUCT TABLE TEST *****/
/* PRODUCT TABLE TEST CASE */
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 0, '���θ޴�00', '���θ޴�00 �Դϴ�.', 6000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 0, '���θ޴�01', '���θ޴�01 �Դϴ�.', 7000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 0, '���θ޴�02', '���θ޴�02 �Դϴ�.', 8000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 0, '���θ޴�03', '���θ޴�03 �Դϴ�.', 8500);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 1, '���̵�޴�00', '���̵�޴�00 �Դϴ�.', 2000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 1, '���̵�޴�01', '���̵�޴�01 �Դϴ�.', 2500);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 1, '���̵�޴�02', '���̵�޴�02 �Դϴ�.', 3000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 1, '���̵�޴�03', '���̵�޴�03 �Դϴ�.', 3000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 2, '�����00', '�����00 �Դϴ�.', 1000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 2, '�����01', '�����01 �Դϴ�.', 1000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 2, '�����02', '�����02 �Դϴ�.', 500);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 2, '�����03', '�����03 �Դϴ�.', 1500);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 3, '��Ʈ00', '���θ޴�00�� ���̵�޴�00 �����00���� �����Ǿ��ֽ��ϴ�.', 9000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 3, '��Ʈ01', '���θ޴�00�� ���̵�޴�02 �����03���� �����Ǿ��ֽ��ϴ�.', 10000);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 3, '��Ʈ02', '���θ޴�02�� ���̵�޴�02 �����00���� �����Ǿ��ֽ��ϴ�.', 9500);
INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, 3, '��Ʈ03', '���θ޴�02�� ���̵�޴�02 �����03���� �����Ǿ��ֽ��ϴ�.', 12000);
UPDATE product SET pgroupid=?, pdetail=?, pname=?, pprice=? WHERE PID=?;
/*search product by pid*/
SELECT p.pid AS PID, p.pgroupid AS PGROUPID, p.pname AS PNAME, p.pprice AS PPRICE, p.pdetail AS PDETAIL FROM PRODUCT P WHERE PID = 1;
/*search product by pgroupname orderby pid*/
SELECT P.* FROM PRODUCT P INNER JOIN PRODUCTGROUP PG ON P.PGROUPID=PG.PGROUPID WHERE PG.PGROUPNAME='���θ޴�' ORDER BY P.PID;


/***** PRODUCTMANAGEMENT TABLE TEST *****/
INSERT INTO PRODUCTMANAGEMENT(PMANAGEMENTID, PID, PDATE) VALUES ();
SELECT PID FROM PRODUCT WHERE PNAME='���θ޴�02';
INSERT INTO PRODUCTMANAGEMENT(PMANAGEMENTID, PID, PDATE) VALUES (PM_PMANAGEMENTID_SEQ.nextval, (SELECT PID FROM PRODUCT WHERE PNAME='���θ޴�02'), SYSDATE);




