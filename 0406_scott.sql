---CREATE TABLE  table_name ( [column_name  data_type] );

/*
�������� : student_score

�й�	id	char(10)
�̸�	name	varchar2(10)
����	kor	number(3)	-999 ~ 999
����	eng	number(3)	-999 ~ 999
����	total	number(3)	-999 ~ 999
���	avg	number(5, 2)	number(���ڸ���, �Ҽ����� �ڸ���)
*/

CREATE TABLE student_score (
    id char(10),
    name varchar2(10),
    kor number(3),
    eng number(3),
    total number(3),
    avg number(5,2)
);
--����Ȯ��
DESC student_score;

--���̺� ����
--DROP TABLE student_score;

/*
���̺� ���� ����
ALTER   TABLE  table_name   ADD ( [ column_name  data_type ] );
                            MODIFY( [ column_name  data_type ] );
                            DROP( [ column_name ] );
*/
--�������� �߰�
ALTER TABLE student_score ADD(math number(3));

--���� �÷� ����
ALTER TABLE student_score DROP(total);
--��� �÷��� �Ҽ��� 1�ڸ��� ����
ALTER TABLE student_score MODIFY(avg number(4,1));

DESC student_score;

-- #################### DML ####################
/*
-INSERT : �Է�
INSERT INTO table_name( columns ) VALUES ( values );

           -UPDATE : ����

                       UPDATE  table_name SETcolumn=value WHEREcondition;

           -DELETE : ����

                       DELETE            FROMtable_name WHEREcondition;

           -SELECT : �˻�

                       SELECT columns FROM table_name WHEREcondition;
*/
INSERT INTO student_score(id, name, kor, eng, math) VALUES('1412345678', 'ȫ�浿', 80, 50, 40);

INSERT INTO student_score(id, name, kor, math) VALUES('2000', 'ȫ����', 50, 80);

COMMIT;

SELECT * FROM student_score;
DELETE FROM student_score;

ROLLBACK;

-- ȫ���� �л��� ������ �����

DELETE FROM student_score WHERE name='ȫ����';

-- 1) ȫ�浿 �л��� �й��� 1000���� ����

UPDATE student_score SET id='1000' WHERE name='ȫ�浿';
ROLLBACK;

-- 2) 1000�� �л��� ���������� 77->44�� ����
UPDATE student_score SET math=44 WHERE id='1000';

-- 3) 2000�� �л��� ���������� 88���� �Է�
UPDATE student_score SET math=88 WHERE id='2000';

-- 4) ��ձ��ϱ�
UPDATE student_score SET avg=(kor+eng+math)/3;
--nvl(math,0) : math�� null���� ��� 0���� ó������

CREATE TABLE emp_copy AS SELECT empno, ename, sal, comm, hiredate, deptno FROM emp;

SELECT * FROM emp_copy;
desc emp_copy;
commit;

--��������
--1
UPDATE emp_copy SET deptno=10 WHERE empno=7782;
--2
UPDATE emp_copy SET ename='ȫ���', sal=3500 WHERE empno=7782;
--3
UPDATE emp_copy SET comm=nvl(comm, 0)+300;
--4
DELETE FROM emp_copy WHERE empno=7499;
--5
DELETE FROM emp_copy WHERE hiredate<TO_DATE(810601);

SELECT * FROM emp_copy WHERE hiredate<'1982-12-31';

--���̺�� : info_tab
/*
�̸� name varchar2(10)
���̵� jumin char(14)
��ȭ tel varchar2(20)
���� gender varchar2(4)
���� age number
����� home varchar2(20)
*/
CREATE TABLE info_tab (name varchar2(10), jumin char(14), tel varchar2(20), gender varchar2(4), age number, home varchar2(20));
desc info_tab;
select * from info_tab;
INSERT INTO info_tab(name, jumin, tel, gender, age, home) VALUES('ȫ�浿', '951230-1234567', '02-222-2222', '����', 25, '����');
INSERT INTO info_tab(name, jumin, tel, gender, age, home) VALUES('ȫ�浿', '951230-1234567', '02-222-2223', '����', 25, '����');
--���̰� 27 ������ ������ ���� �Է�
INSERT INTO info_tab(name, jumin, tel, gender, age, home) VALUES('������', '941230-1234567', '02-222-2222', '����', 27, '����');
INSERT INTO info_tab(name, tel, gender, age) VALUES('ȫ����', '011-1111-2222', '����', 27);

DROP TABLE info_tab;

CREATE TABLE info_tab (
name varchar2(10) not null, -- �̸��� �ʼ� �Է»����Դϴ�.
jumin char(14), 
tel varchar2(20), 
gender varchar2(4) not null, 
age number not null, 
home varchar2(20),
CONSTRAINT pk_info_tel PRIMARY KEY(tel)
);
UPDATE info_tab SET jumin='987654-1234567' WHERE tel='02-222-2223';

ALTER TABLE info_tab ADD CONSTRAINT  un_info_jumin UNIQUE(jumin);

select constraint_name, constraint_type from user_constraints where table_name='INFO_TAB';

ALTER TABLE info_tab ADD CONSTRAINT ck_info_gender CHECK(gender IN('����', '����'));

--PK�� �ش��ϴ� ������ '����'���� ����

UPDATE info_tab SET gender='����' WHERE TEL='02-222-3222';
DELETE FROM info_tab WHERE name='ȫ����';


ALTER  TABLE  info_tab MODIFY  gender varchar2(4)  DEFAULT '����';

INSERT INTO info_tab(name, tel, gender, age) VALUES ('ȫ����', '051-5555', '����', 15);
INSERT INTO info_tab(name, tel, age) VALUES('ȫ����', '011-2345', 14);

select * from info_tab;

drop table info_tab; --�ѹ��ص� ������ �ȵ� (�ѹ��� DML���� ����)

CREATE TABLE info_tab(
    name varchar2(10) NOT NULL,
    jumin char(14) UNIQUE,
    tel varchar2(20) PRIMARY KEY,
    gender varchar2(4) DEFAULT '����' CHECK(gender in ('����', '����')),
    home varchar2(20)  
);

CREATE TABLE info_tab(
    name varchar2(10) NOT NULL,
    jumin char(14),
    tel varchar2(20),
    gender varchar2(4) default '����',
    home varchar2(20),
    age number,
    CONSTRAINT unique_info_tab_jumin UNIQUE(jumin), -- �ߺ� ��� x
    CONSTRAINT primarykey_info_tab_tel PRIMARY KEY(tel),
    CONSTRAINT check_info_tab_gender CHECK(gender in ('����', '����'))
);

--�׽�Ʈ ������
INSERT INTO info_tab( name, tel, jumin )
VALUES('������', '02-777-4444', '990909-1234567');

INSERT INTO info_tab( name, tel, jumin, gender, age, home )
VALUES('������','03-555-5555', '880808-1234567', '����', 27,'���');

INSERT INTO info_tab( name, tel, jumin, gender, age, home )
VALUES('ȫ�浿','03-031-0310', '900909-1234567', '����', 27,'���'); -- gender üũ �������ǿ� �ɸ�

INSERT INTO info_tab( name, jumin) VALUES('ȫ����', '550505-1234567'); -- primary key �Է��� �ʿ���

INSERT INTO info_tab( tel, jumin ) VALUES('031-777-7777', '700707-1234567'); -- �̸��� �Է��������(not null)
INSERT INTO info_tab( name, tel, jumin ) VALUES('������', '031-000-0000', '700707-1234567');

DESC INFO_TAB;

select * from INFO_TAB;
--emp copy�� ����� 7654�� ȫ�浿 ����� ������ �Է��ϱ�
select * from emp_copy;
INSERT INTO emp_copy(empno, ename) VALUES(7654, 'ȫ�浿');
DELETE FROM emp_copy WHERE ename='ȫ�浿';

ALTER TABLE emp_copy ADD CONSTRAINT pk_emp_copy_empno PRIMARY KEY(empno);

--emp�� ����� 7369�� ����� ������ �Է��ϱ�
INSERT INTO emp(empno, ename) VALUES(7369, 'ȫ�浿');

--emp table�� ��� 8888�� ������ ����� ������ �Է��ϵ� �μ���ȣ�� 99�� �Է�
INSERT INTO emp(empno, ename, deptno) VALUES(8888, '������', 99);
--emp_copy table�� ��� 8888�� ������ ����� ������ �Է��ϵ� �μ���ȣ�� 99�� �Է�
INSERT INTO emp_copy(empno, ename, deptno) VALUES(8888, '������', 99);
DELETE FROM emp_copy WHERE empno=8888;

ALTER TABLE emp_copy ADD CONSTRAINT fk_emp_copy_deptno FOREIGN KEY(deptno) REFERENCES dept (deptno);


SELECT constraint_name, constraint_type FROM user_constraints WHERE table_name='EMP_COPY';

/* ���� �� ����

1. ���� chapter 8, 9, 10

2. ����

*/
--1
CREATE TABLE sawon(
    sabun number(6),
    sawon_name varchar2(10),
    ubmu varchar2(20),
    weolgub number(10, 2),
    buseo number(3)
);
--2
ALTER TABLE sawon ADD CONSTRAINT pk_sawon_sabun PRIMARY KEY(sabun);
--3
ALTER TABLE sawon ADD jiyeok varchar(20) NOT NULL;
--4
ALTER TABLE sawon MODIFY weolgub number(7);
--5
ALTER TABLE sawon ADD CONSTRAINT check_sawon_ubmu CHECK (ubmu in('����', '��������', '����'));
--6
ALTER TABLE sawon MODIFY ubmu DEFAULT '����';
--7
CREATE TABLE buseo(
    buseo_no number(3),
    buseo_name varchar2(20),
    CONSTRAINT pk_buseo_buseo_no PRIMARY KEY(buseo_no) 
);
--8
ALTER TABLE sawon ADD CONSTRAINT fk_sawon_buseo FOREIGN KEY(buseo) REFERENCES buseo(buseo_no);
--9
INSERT INTO buseo(buseo_no, buseo_name) VALUES(101, '����Ʈ��������������');
INSERT INTO buseo(buseo_no, buseo_name) VALUES(202, '������');
INSERT INTO buseo(buseo_no, buseo_name) VALUES(303, '�����ڿ���');
--10
INSERT INTO sawon(sabun, sawon_name, weolgub, buseo, jiyeok) VALUES (8001, 'ȫ�浿�̱�', 100000, 101, '�λ�');
INSERT INTO sawon(sabun, sawon_name, ubmu, weolgub, buseo, jiyeok) VALUES (8002, 'ȫ����', '�繫', 200000, 202, '��õ'); --������ check ��������
INSERT INTO sawon(sabun, sawon_name, ubmu, buseo, jiyeok) VALUES (8003, 'ȫ���', '����', 111, '����'); --�μ� �ܷ�Ű ��������
INSERT INTO sawon(sabun, sawon_name, weolgub, jiyeok) VALUES (8004, 'ȫ�漮', 12345678, '����'); --���� �ڸ��� �ʰ�
INSERT INTO sawon(sabun, sawon_name, ubmu, buseo, jiyeok) VALUES (8005, 'ȫ��ö', '��������', 303, '�Ǳ�');
--11
ALTER TABLE sawon DROP COLUMN jiyeok;
--12
DELETE FROM buseo WHERE buseo_name='�����ڿ���'; -- �ڽ� ���ڵ尡 �����ؼ� ������ ���� ����
--13 �����Ϳ� ��������� ����
-- delete�� �����ʹ� ���������� ���̺� �뷮�� �پ���� �ʰ�, �ѹ��� ����
-- truncate ���̺� �뷮�� �پ���, �ѹ��� �Ұ���
TRUNCATE TABLE sawon;
--14
CREATE TABLE buseo(
    buseo_no number(3),
    buseo_name varchar2(20),
    CONSTRAINT pk_buseo_buseo_no PRIMARY KEY(buseo_no)
);
CREATE TABLE sawon(
    sabun number(6),
    sawon_name varchar2(10),
    ubmu varchar2(20) DEFAULT '����',
    weolgub varchar2(14),
    buseo number(3),
    jiyeok varchar2(20) NOT NULL,
    CONSTRAINT pk_sawon_sabun PRIMARY KEY(sabun),
    CONSTRAINT ck_sawon_ubmu CHECK(ubmu in('����', '��������', '����')),
    CONSTRAINT fk_sawon_buseo FOREIGN KEY(buseo) REFERENCES buseo(buseo_no)
);






